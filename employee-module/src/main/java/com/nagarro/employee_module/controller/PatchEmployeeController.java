package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.service.PatchEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/v1/api")
public class PatchEmployeeController {
    private final PatchEmployeeService patchEmployeeService;

    public PatchEmployeeController(PatchEmployeeService patchEmployeeService) {
        this.patchEmployeeService = patchEmployeeService;
    }

    @PatchMapping("/employees/patch/{employeeId}")
    public ResponseEntity<EmployeeDTO> patchEmployeeById(@PathVariable int employeeId, @RequestBody Map<String,Object> patches){
        EmployeeDTO patchedEmployee = patchEmployeeService.patchEmployee(employeeId, patches);
        return new ResponseEntity<>(patchedEmployee, HttpStatus.OK);
    }
}
