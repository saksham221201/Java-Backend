package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.service.DeleteAllEmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class DeleteAllEmployeesController {
    private final DeleteAllEmployeesService deleteAllEmployeesService;

    public DeleteAllEmployeesController(DeleteAllEmployeesService deleteAllEmployeesService) {
        this.deleteAllEmployeesService = deleteAllEmployeesService;
    }

    @DeleteMapping("/employees/delete/all")
    public ResponseEntity<Void> deleteAllEmployees(){
        deleteAllEmployeesService.deleteAllEmployees();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
