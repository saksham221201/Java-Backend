package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.*;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/v1/api/employees")
public class EmployeeController {

    private final AllEmployeesService getAllEmployeesService;
    private final NewEmployeeService createEmployeeService;
    private final EmployeeByIdService getEmployeeByIdService;
    private final UpdateEmployeeService updateEmployeeService;
    private final EmployeesByDepartmentService employeesByDepartmentService;
    private final PatchEmployeeService patchEmployeeService;
    private final DeleteAllEmployeesService deleteAllEmployeesService;
    private final DeleteByIdService deleteByIdService;

    // It provides immutability and null pointer exception
    public EmployeeController(NewEmployeeService createEmployeeService, AllEmployeesService getAllEmployeesService, EmployeeByIdService getEmployeeByIdService, UpdateEmployeeService updateEmployeeService, EmployeesByDepartmentService employeesByDepartmentService, PatchEmployeeService patchEmployeeService, DeleteAllEmployeesService deleteAllEmployeesService, DeleteByIdService deleteByIdService) {
        this.createEmployeeService = createEmployeeService;
        this.getAllEmployeesService = getAllEmployeesService;
        this.getEmployeeByIdService = getEmployeeByIdService;
        this.updateEmployeeService = updateEmployeeService;
        this.employeesByDepartmentService=employeesByDepartmentService;
        this.patchEmployeeService=patchEmployeeService;
        this.deleteAllEmployeesService=deleteAllEmployeesService;
        this.deleteByIdService = deleteByIdService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        List<EmployeeDTO> employees = getAllEmployeesService.getAllEmployees();
        return new ResponseEntity<>(employees, HttpStatus.OK);
    }

    @GetMapping("/id/{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable int employeeId) {
        EmployeeDTO employee = getEmployeeByIdService.getEmployeeById(employeeId);
        return new ResponseEntity<>(employee, HttpStatus.OK);
    }

    @GetMapping("/by-department")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(@RequestParam String department){
        List<EmployeeDTO> employees = employeesByDepartmentService.getEmployeeByDepartment(department);
        return new ResponseEntity<>(employees,HttpStatus.FOUND);
    }

    @PostMapping("/new")
    public ResponseEntity<Employee> createNewEmployee(@Valid @RequestBody EmployeeDTO employeeDTO){
        Employee newEmployee = createEmployeeService.createEmployee(employeeDTO);
        return new ResponseEntity<>(newEmployee, HttpStatus.CREATED);
    }

    @PutMapping("/update/{employeeId}")
    public ResponseEntity<Employee> updateEmployeeById(@Valid @PathVariable int employeeId, @RequestBody EmployeeDTO employeeDTO) {
        Employee updatedEmployee = updateEmployeeService.updateEmployee(employeeId, employeeDTO);
        return new ResponseEntity<>(updatedEmployee, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{employeeId}")
    public ResponseEntity<Void> deleteById(@PathVariable int employeeId) {
        deleteByIdService.deleteById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/patch/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable int employeeId, @RequestBody Map<String,Object> patches){
        EmployeeDTO patchedEmployee = patchEmployeeService.patchEmployee(employeeId, patches);
        return new ResponseEntity<>(patchedEmployee,HttpStatus.OK);
    }

    @DeleteMapping("/delete/all")
    public ResponseEntity<Void> deleteAllEmployees(){
        deleteAllEmployeesService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
