package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;

import java.util.Map;

@FunctionalInterface
public interface PatchEmployeeService {
    EmployeeDTO patchEmployee(int employeeId, Map<String,Object> employee);
}
