package com.example.employeeservice.service;

import com.example.employeeservice.entity.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    public Employee getEmployee() {

        Employee employee = new Employee();

        employee.setId(101);
        employee.setFirstName("Arindam");
        employee.setLastName("Tiwari");
        employee.setDepartment("DevOps");
        employee.setSalary(1800000);

        return employee;
    }
}