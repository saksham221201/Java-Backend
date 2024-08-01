package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dao.EmployeeDao;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeDao.findAll();
    }
}
