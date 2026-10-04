package com.example.AOPDemo.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspects {

    @Before("execution(String com.example.AOPDemo.service.StudentService.createStudent())")
    public void logBeforeMethod() {
        System.out.println("LogBeforee Method is called");
    }

    @AfterReturning ("execution(String com.example.AOPDemo.service.StudentService.createStudent())")
    public void logAfterReturningMethod() {
        System.out.println("LogAfterReturningMethod is called");
    }

    @AfterThrowing("execution(String com.example.AOPDemo.service.StudentService.createStudent())")
    public void logAfterThrowing() {
        System.out.println("LogAfterThrowing is here.....");
    }

    @After("execution(String com.example.AOPDemo.service.StudentService.createStudent())")
    public void logAfter() {
        System.out.println("After is called beacause it is finally boy");
    }

    @Around("execution(String com.example.AOPDemo.service.StudentService.createStudent())")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around - Before");

        Object result = joinPoint.proceed();

        System.out.println("Around - After");

        return result;
    }

}
