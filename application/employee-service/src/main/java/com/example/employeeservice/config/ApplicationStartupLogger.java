package com.example.employeeservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupLogger {

    @Value("${server.port:8081}")
    private String port;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {

        System.out.println();
        System.out.println("======================================================");
        System.out.println(" Employee Service Started Successfully");
        System.out.println("======================================================");
        System.out.println(" Application URL : http://localhost:" + port + "/employees");
        System.out.println(" Health Check    : http://localhost:" + port + "/actuator/health");
        System.out.println(" Info Endpoint   : http://localhost:" + port + "/actuator/info");
        System.out.println("======================================================");
        System.out.println();
    }
}