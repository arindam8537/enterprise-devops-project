package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    @Test
    void testGetAllEmployees() {

        Employee employee = new Employee(
                1L,
                "Arindam",
                "Tiwari",
                "arindam@gmail.com",
                "DevOps",
                90000.0
        );

        when(employeeRepository.findAll())
                .thenReturn(List.of(employee));

        List<Employee> employees = employeeService.getAllEmployees();

        assertEquals(1, employees.size());
        assertEquals("Arindam", employees.get(0).getFirstName());

        verify(employeeRepository, times(1)).findAll();
    }

    @Test
    void testSaveEmployee() {

        Employee employee = new Employee(
                null,
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "IT",
                70000.0
        );

        Employee savedEmployee = new Employee(
                1L,
                "Rahul",
                "Sharma",
                "rahul@gmail.com",
                "IT",
                70000.0
        );

        when(employeeRepository.save(employee))
                .thenReturn(savedEmployee);

        Employee result = employeeService.saveEmployee(employee);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Rahul", result.getFirstName());

        verify(employeeRepository, times(1))
                .save(employee);
    }
}