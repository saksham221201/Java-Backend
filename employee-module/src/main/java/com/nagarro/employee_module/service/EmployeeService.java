package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeResponseDTO;

import java.util.List;

public interface EmployeeService {
    List<EmployeeResponseDTO> getAllEmployees();
    EmployeeResponseDTO getEmployeeById(int employeeId);
}
