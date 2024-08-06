package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.NewEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class NewEmployeeController {
    private final NewEmployeeService newEmployeeService;

    public NewEmployeeController(NewEmployeeService newEmployeeService) {
        this.newEmployeeService = newEmployeeService;
    }

    @PostMapping("/employees/new")
    public ResponseEntity<Employee> createNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = newEmployeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
}
