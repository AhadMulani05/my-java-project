package com.example.AOPAdvance.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspects {

//    @Before("execution(* com.example.AOPAdvance.service.StudentService.createStudent(..))")
//    public void logBeforeStudent() {
//        System.out.println("Before aspects is here");
//    }

    @Before("@annotation(com.example.AOPAdvance.annotation.TrackExecutionTime)")
    public void logBeforeStudent() {
        System.out.println("Before aspects is here");
    }

}
