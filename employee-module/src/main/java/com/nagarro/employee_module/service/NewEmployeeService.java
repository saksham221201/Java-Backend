package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;

@FunctionalInterface
public interface NewEmployeeService {
    Employee createEmployee(EmployeeDTO employee);
}
