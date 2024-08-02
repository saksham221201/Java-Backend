package com.nagarro.employee_module.service;

import com.nagarro.employee_module.dto.EmployeeDTO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FunctionalInterface
public interface EmployeesByDepartmentService {
    List<EmployeeDTO> getEmployeeByDepartment(@RequestParam String department);
}
