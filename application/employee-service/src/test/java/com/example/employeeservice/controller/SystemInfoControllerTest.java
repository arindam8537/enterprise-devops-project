package com.example.employeeservice.controller;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SystemInfoController.class)
class SystemInfoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testSystemInfo() throws Exception {

        mockMvc.perform(get("/system/info"))
                .andExpect(status().isOk())

                .andExpect(jsonPath("$.application").value("Employee Service"))
                .andExpect(jsonPath("$.status").value("UP"))
                .andExpect(jsonPath("$.version").value("1.0.0"))

                .andExpect(jsonPath("$.javaVersion").exists())
                .andExpect(jsonPath("$.hostName").exists())
                .andExpect(jsonPath("$.serverTime").exists())

                .andExpect(jsonPath("$.disk").exists())
                .andExpect(jsonPath("$.memory").exists())
                .andExpect(jsonPath("$.cpu").exists());
    }
}