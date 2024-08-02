package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.UpdateEmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UpdateEmployeeServiceImpl implements UpdateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public UpdateEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee updateEmployee(int employeeId, EmployeeDTO employee) {
        employeeRepository.findById(employeeId).orElseThrow(() -> new RecordNotFoundException("Employee with id " + employeeId + " not found!", HttpStatus.NOT_FOUND.value()));
        Employee updatedEmployee = Employee
                .builder()
                .employeeId(employeeId)
                .employeeName(employee.getEmployeeName())
                .department(employee.getDepartment())
                .employeeMobiles(employee.getEmployeeMobiles())
                .address(employee.getAddress())
                .emails(employee.getEmails())
                .build();
        return employeeRepository.save(updatedEmployee);
    }
}
