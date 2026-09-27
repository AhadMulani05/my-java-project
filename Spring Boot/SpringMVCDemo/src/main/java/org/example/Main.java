package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.config.StudentConfigaration;
import org.springframework.cglib.proxy.Dispatcher;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.File;

public class Main {
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomacat = new Tomcat();

        tomacat.setPort(8080);

        tomacat.getConnector();

        String contextpath = "";
        String baseDoc = new File("src/main/webapp").getAbsolutePath();

        Context context = tomacat.addContext(contextpath, baseDoc);

        //IOC Container
        AnnotationConfigWebApplicationContext springContext = new AnnotationConfigWebApplicationContext();

        springContext.register(StudentConfigaration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(springContext);

        Tomcat.addServlet(context, "dispatcherServlet", dispatcherServlet);

        context.addServletMappingDecoded("/", "dispatcherServlet");

        tomacat.start();

        System.out.println("Tomcat started at port 8080");

        tomacat.getServer().await();
    }
}