package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.entity.MobileNumber;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.NewEmployeeService;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewEmployeeServiceImpl implements NewEmployeeService {

    private final EmployeeRepository employeeRepository;

    public NewEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {

        if (employeeDTO.getSalary() == 0.0){
            throw new IllegalArgumentException("Employee salary can't be 0");
        }

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
                .salary(employeeDTO.getSalary())
                .address(employeeDTO.getAddress())
                .department(employeeDTO.getDepartment())
                .build();

        return employeeRepository.save(employee);
    }
}
