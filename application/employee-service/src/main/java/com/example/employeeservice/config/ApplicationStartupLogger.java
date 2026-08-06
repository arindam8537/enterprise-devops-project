package com.example.employeeservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ApplicationStartupLogger {

        private static final Logger log =
            LoggerFactory.getLogger(ApplicationStartupLogger.class);

    @Value("${server.port:8081}")
    private String port;

    @EventListener(ApplicationReadyEvent.class)
    public void applicationReady() {


        log.info("======================================================");
        log.info(" Employee Service Started Successfully");
        log.info("======================================================");
        log.info(" Application URL : http://localhost:" + port + "/employees");
        log.info(" System Info     : http://localhost:" + port + "/system/info");
        log.info("--------------- Spring Boot Actuator ----------------");
        log.info(" Health Check    : http://localhost:" + port + "/actuator/health");
        log.info(" Info Endpoint   : http://localhost:" + port + "/actuator/info");
        log.info("======================================================");
    }
}