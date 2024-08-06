package com.nagarro.employee_module.controller;

import com.nagarro.employee_module.service.DeleteByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/api")
public class DeleteByIdController {
    private final DeleteByIdService deleteByIdService;

    public DeleteByIdController(DeleteByIdService deleteByIdService) {
        this.deleteByIdService = deleteByIdService;
    }

    @DeleteMapping("/employees/delete/{employeeId}")
    public ResponseEntity<Void> deleteById(@PathVariable int employeeId) {
        deleteByIdService.deleteById(employeeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
