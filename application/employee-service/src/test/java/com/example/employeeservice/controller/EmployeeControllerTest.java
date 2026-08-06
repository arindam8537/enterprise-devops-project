package com.example.employeeservice.controller;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.service.EmployeeService;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    void testGetEmployees() throws Exception {

        Employee employee = new Employee(
                1L,
                "Arindam",
                "Tiwari",
                "arindam@gmail.com",
                "DevOps",
                90000.0
        );

        when(employeeService.getAllEmployees())
                .thenReturn(List.of(employee));

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].firstName").value("Arindam"))
                .andExpect(jsonPath("$[0].department").value("DevOps"));
    }

    @Test
    void testSaveEmployee() throws Exception {

        Employee employee = new Employee(
                1L,
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "IT",
                70000.0
        );

        when(employeeService.saveEmployee(org.mockito.ArgumentMatchers.any()))
                .thenReturn(employee);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                          "firstName":"Rahul",
                          "lastName":"Sharma",
                          "email":"rahul@gmail.com",
                          "department":"IT",
                          "salary":70000
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Rahul"))
                .andExpect(jsonPath("$.department").value("IT"));
    }
}