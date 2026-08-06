package com.example.employeeservice.controller;

import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public Employee saveEmployee(@Valid @RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/error-test")
    public String error() {
        throw new RuntimeException("Demo Exception from Employee Service");

}
}
