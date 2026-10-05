package com.ahad.jobconnect.repository;

import com.ahad.jobconnect.entity.Job;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

/**
 * Builds dynamic search filters for jobs using the JPA Criteria API.
 */
public final class JobSpecifications {

    private JobSpecifications() {
    }

    public static Specification<Job> search(String keyword, String location, String skill, Integer experience) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.isTrue(root.get("active")));

            if (hasText(keyword)) {
                String like = "%" + keyword.trim().toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("title")), like),
                        cb.like(cb.lower(root.get("description")), like)));
            }
            if (hasText(location)) {
                predicates.add(cb.like(cb.lower(root.get("location")), "%" + location.trim().toLowerCase() + "%"));
            }
            if (hasText(skill)) {
                predicates.add(cb.like(cb.lower(root.get("skills")), "%" + skill.trim().toLowerCase() + "%"));
            }
            if (experience != null) {
                // jobs whose required experience is at most what the candidate has
                predicates.add(cb.lessThanOrEqualTo(root.get("minExperience"), experience));
            }
            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    private static boolean hasText(String s) {
        return s != null && !s.isBlank();
    }
}
