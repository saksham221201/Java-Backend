package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.EmployeesByDepartmentService;
import com.nagarro.employee_module.service.NewEmployeeService;
import com.nagarro.employee_module.service.AllEmployeesService;
import com.nagarro.employee_module.service.EmployeeByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    private final AllEmployeesService getAllEmployeesService;
    private final NewEmployeeService createEmployeeService;
    private final EmployeeByIdService getEmployeeByIdService;
    private final EmployeesByDepartmentService employeesByDepartmentService;

    // It provides immutability and null pointer exception
    public EmployeeController(NewEmployeeService createEmployeeService, AllEmployeesService getAllEmployeesService, EmployeeByIdService getEmployeeByIdService,EmployeesByDepartmentService employeesByDepartmentService) {
        this.createEmployeeService = createEmployeeService;
        this.getAllEmployeesService = getAllEmployeesService;
        this.getEmployeeByIdService = getEmployeeByIdService;
        this.employeesByDepartmentService=employeesByDepartmentService;
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

    @PostMapping("/new")
    public ResponseEntity<Employee> createNewEmployee(@RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = createEmployeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@RequestParam String department){
        List<EmployeeDTO> employees = employeesByDepartmentService.getEmployeeByDepartment(department);
        return new ResponseEntity<>(employees,HttpStatus.FOUND);
    }
}
