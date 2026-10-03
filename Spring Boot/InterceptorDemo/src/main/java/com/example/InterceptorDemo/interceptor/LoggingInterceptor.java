package com.example.InterceptorDemo.interceptor;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Component
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {

        System.out.println("Incoming Request......");

        System.out.println("Http methods: " + request.getMethod());
        System.out.println("Request URI: " + request.getRequestURI());
        System.out.println("Client IP: " + request.getRemoteAddr());

        if(handler instanceof HandlerMethod handlerMethod) {
            System.out.println("Controller: " + handlerMethod.getBeanType().getName());
            System.out.println("Controller Method: " + handlerMethod.getMethod().getName());
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response,
                           Object handler,
                           ModelAndView modelAndView) throws Exception {
        System.out.println("Posthandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response,
                                Object handler,
                                Exception ex) throws Exception {
        System.out.println("AfterCompletion");
        System.out.println("Response status: " + response.getStatus());
    }
}
