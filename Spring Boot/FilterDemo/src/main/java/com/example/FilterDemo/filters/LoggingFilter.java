package com.example.FilterDemo.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.http.HttpRequest;

@Component
@Order (1)
public class LoggingFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain)
            throws IOException, ServletException {

        long startTime = System.currentTimeMillis();

        HttpServletRequest httpServletRequest =
                (HttpServletRequest) servletRequest;

        HttpServletResponse httpServletResponse =
                (HttpServletResponse) servletResponse;

        System.out.println("Enter in the Logging Filter");

        System.out.println(httpServletRequest.getMethod() + " " +
                            httpServletRequest.getRequestURI());

        filterChain.doFilter(servletRequest, servletResponse);

        System.out.println(httpServletResponse.getStatus());

        long totalTime = System.currentTimeMillis() - startTime;

        System.out.println(totalTime);

        System.out.println("Existing from logging filter");

    }
}
