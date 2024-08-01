package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;

import java.util.List;

public interface EmployeeService {
//    List<EmployeeDTO> getAllEmployees();
//    EmployeeDTO getEmployeeById(int employeeId);
    public Employee createEmployee(EmployeeDTO employee);
}
