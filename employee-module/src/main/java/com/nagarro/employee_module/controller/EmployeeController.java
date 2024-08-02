package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.CreateEmployeeService;
import com.nagarro.employee_module.service.GetAllEmployeesService;
import com.nagarro.employee_module.service.GetEmployeeByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    private final GetAllEmployeesService getAllEmployeesService;
    private final CreateEmployeeService createEmployeeService;
    private final GetEmployeeByIdService getEmployeeByIdService;

    // It provides immutability and null pointer exception
    public EmployeeController(CreateEmployeeService createEmployeeService, GetAllEmployeesService getAllEmployeesService, GetEmployeeByIdService getEmployeeByIdService) {
        this.createEmployeeService = createEmployeeService;
        this.getAllEmployeesService = getAllEmployeesService;
        this.getEmployeeByIdService = getEmployeeByIdService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = getAllEmployeesService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/id/{}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDTO employee = getEmployeeByIdService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = createEmployeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }
}
