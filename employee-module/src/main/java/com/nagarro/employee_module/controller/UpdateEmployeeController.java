package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.UpdateEmployeeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api")
public class UpdateEmployeeController {
    private final UpdateEmployeeService updateEmployeeService;

    public UpdateEmployeeController(UpdateEmployeeService updateEmployeeService) {
        this.updateEmployeeService = updateEmployeeService;
    }

    @PutMapping("/employees/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(@Valid @PathVariable int employeeId, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = updateEmployeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

}
