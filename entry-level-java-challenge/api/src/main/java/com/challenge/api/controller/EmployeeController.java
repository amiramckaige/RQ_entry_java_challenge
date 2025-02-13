package com.challenge.api.controller;

import com.challenge.api.model.Employee;
import java.util.List;
import java.util.UUID;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

/**
 * Amira McKaige
 * Feb 2025
 * Purpose: This file defines the EmployeeController class which handles HTTP requests of employees. 
 *          The 3 Endpoints are getAllEmployees(), getEmployeeByUid(UUID uuid), and createEmployee(Employee employee)
 *          A secure REST API allows the Employees-R-Us platform to grab information from the current system
 */
@RestController  // mark class as REST controller
@RequestMapping("/api/v1/employee")  //define URL for requests that are employee-related
public class EmployeeController {

    private final EmployeeService employeeService;  // declare EmployeeService dield (business logic)

    // inject constructor into controller
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;  // auto inject EmployeeService instance
    }

    /**
     * handle GET requests to retreive all employees
     * output: a list of all employees
     */
    @GetMapping  // handles GET requests to /api/v1/employee
    public List<Employee> getAllEmployees() {
        // tell service layer to fetch all employees and return the result
        return employeeService.getAllEmployees();
    }

    /**
     * handle GET requests to fetch a specific employee by their UUID
     * input:  uuid the UUID of the employee to retrieve
     * output: the requested employee if found
     * throw:  ResponseStatusException if the employee is not found (404 Not Found)
     */
    @GetMapping("/{uuid}")  // Handles GET requests to /api/v1/employee/{uuid}
    public Employee getEmployeeByUuid(@PathVariable UUID uuid) {
        // tell service layer to fetch employee by UUID
        Employee employee = employeeService.getEmployeeByUuid(uuid);
        
        // throw 404 if employee not found
        if (employee == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Employee not found");
        }
        
        // return employee if found
        return employee;
    }

    /**
     * handle POST requests to make new employee
     * input:  requestBody  employee data 
     * output: newly created employee with a UUID
     */
    @PostMapping  // Handles POST requests to /api/v1/employee
    public Employee createEmployee(@RequestBody Employee requestBody) {
        // tell service layer to make new employee and return result
        return employeeService.createEmployee(requestBody);
    }
}
