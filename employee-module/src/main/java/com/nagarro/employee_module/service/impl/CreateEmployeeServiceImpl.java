package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.entity.MobileNumber;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.CreateEmployeeService;

import java.util.Set;
import java.util.stream.Collectors;

public class CreateEmployeeServiceImpl implements CreateEmployeeService {

    private final EmployeeRepository employeeRepository;

    public CreateEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {

        Set<MobileNumber> mobileNumbers = employeeDTO.getEmployeeMobiles().stream()
                .map(n->MobileNumber.builder()
                        .number(n.getNumber())
                        .build())
                .collect(Collectors.toSet());

        Set<Email> emails = employeeDTO.getEmails().stream()
                .map(e->Email.builder()
                        .employeeEmail(e.getEmployeeEmail())
                        .build())
                .collect(Collectors.toSet());

        Employee employee = Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeMobiles(mobileNumbers)
                .emails(emails)
                .address(employeeDTO.getAddress())
                .department(employeeDTO.getDepartment())
                .build();

        return employeeRepository.save(employee);
    }
}
