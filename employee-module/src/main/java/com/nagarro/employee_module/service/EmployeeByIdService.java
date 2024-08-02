package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;

@FunctionalInterface
public interface EmployeeByIdService {
    EmployeeDTO getEmployeeById(int employeeId);
}
