package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;

@FunctionalInterface
public interface GetEmployeeByIdService {
    EmployeeDTO getEmployeeById(int employeeId);
}
