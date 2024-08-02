package com.nagarro.employee_module.service;


import com.nagarro.employee_module.dto.EmployeeDTO;

import java.util.List;

@FunctionalInterface
public interface GetAllEmployeesService {
    List<EmployeeDTO> getAllEmployees();
}
