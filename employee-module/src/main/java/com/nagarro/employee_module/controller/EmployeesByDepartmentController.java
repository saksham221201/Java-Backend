package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.service.EmployeesByDepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api")
public class EmployeesByDepartmentController {

    private final EmployeesByDepartmentService employeesByDepartmentService;

    public EmployeesByDepartmentController(EmployeesByDepartmentService employeesByDepartmentService) {
        this.employeesByDepartmentService = employeesByDepartmentService;
    }

    @GetMapping("/employees/by-department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@RequestParam String department){
        List<EmployeeDTO> employees = employeesByDepartmentService.getEmployeeByDepartment(department);
        return new ResponseEntity<>(employees, HttpStatus.FOUND);
    }
}
