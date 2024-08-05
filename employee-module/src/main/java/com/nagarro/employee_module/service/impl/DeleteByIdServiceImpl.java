package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.DeleteByIdService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteByIdServiceImpl implements DeleteByIdService {

    private final EmployeeRepository employeeRepository;

    public DeleteByIdServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteById(int employeeId) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new RecordNotFoundException("Employee does not exists with id: " + employeeId, HttpStatus.NOT_FOUND.value());
        }
        employeeRepository.deleteById(employeeId);
    }
}
