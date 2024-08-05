package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.DeleteAllEmployeesService;
import org.springframework.stereotype.Service;

@Service
public class DeleteAllEmployeesServiceImpl implements DeleteAllEmployeesService {

    private final EmployeeRepository employeeRepository;

    public DeleteAllEmployeesServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void deleteAllEmployees() {
        employeeRepository.deleteAll();
    }
}
