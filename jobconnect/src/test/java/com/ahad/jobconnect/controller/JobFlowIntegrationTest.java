package com.ahad.jobconnect.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Map;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * End-to-end test: real Spring context + security filter chain + in-memory H2 database.
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class JobFlowIntegrationTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;

    private String registerAndGetToken(String role) throws Exception {
        String email = role.toLowerCase() + "-" + UUID.randomUUID() + "@test.com";
        String body = objectMapper.writeValueAsString(Map.of(
                "name", role + " User", "email", email, "password", "secret123", "role", role));
        String json = mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        JsonNode node = objectMapper.readTree(json);
        return node.get("token").asText();
    }

    private String jobJson() throws Exception {
        return objectMapper.writeValueAsString(Map.of(
                "title", "Java Backend Developer",
                "description", "Build REST APIs with Spring Boot",
                "location", "Pune",
                "skills", "Java,Spring Boot,MySQL",
                "minExperience", 0,
                "salary", 600000));
    }

    @Test
    void protectedEndpoint_withoutToken_returns401() throws Exception {
        mockMvc.perform(get("/api/jobs")).andExpect(status().isUnauthorized());
    }

    @Test
    void login_withWrongPassword_returns401() throws Exception {
        String body = objectMapper.writeValueAsString(Map.of("email", "nobody@test.com", "password", "wrong"));
        mockMvc.perform(post("/api/auth/login").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void register_withInvalidEmail_returns400() throws Exception {
        String body = objectMapper.writeValueAsString(Map.of(
                "name", "Bad", "email", "not-an-email", "password", "secret123", "role", "CANDIDATE"));
        mockMvc.perform(post("/api/auth/register").contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isBadRequest());
    }

    @Test
    void candidate_cannotPostJob_returns403() throws Exception {
        String token = registerAndGetToken("CANDIDATE");
        mockMvc.perform(post("/api/jobs")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON).content(jobJson()))
                .andExpect(status().isForbidden());
    }

    @Test
    void fullFlow_recruiterPosts_candidateSearchesAndApplies_duplicateRejected() throws Exception {
        String recruiter = registerAndGetToken("RECRUITER");
        String candidate = registerAndGetToken("CANDIDATE");

        String created = mockMvc.perform(post("/api/jobs")
                        .header("Authorization", "Bearer " + recruiter)
                        .contentType(MediaType.APPLICATION_JSON).content(jobJson()))
                .andExpect(status().isCreated())
                .andReturn().getResponse().getContentAsString();
        long jobId = objectMapper.readTree(created).get("id").asLong();

        mockMvc.perform(get("/api/jobs")
                        .param("skill", "spring")
                        .param("location", "pune")
                        .header("Authorization", "Bearer " + candidate))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.totalElements").isNumber());

        mockMvc.perform(post("/api/jobs/" + jobId + "/apply")
                        .header("Authorization", "Bearer " + candidate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"coverLetter\":\"Interested!\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.status").value("APPLIED"));

        mockMvc.perform(post("/api/jobs/" + jobId + "/apply")
                        .header("Authorization", "Bearer " + candidate)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isConflict());
    }
}
