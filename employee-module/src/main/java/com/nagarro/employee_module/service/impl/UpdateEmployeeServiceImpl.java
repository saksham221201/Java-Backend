package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.UpdateEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateEmployeeServiceImpl implements UpdateEmployeeService {

    private final EmployeeRepository employeeRepository;

    Logger logger = LoggerFactory.getLogger(UpdateEmployeeServiceImpl.class);

    public UpdateEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee updateEmployee(int employeeId, EmployeeDTO employeeDTO) {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isEmpty()) {
            throw new RecordNotFoundException("Employee with id: " + employeeId + " does not exists", HttpStatus.NOT_FOUND.value());
        }
        if (employeeDTO.getSalary() == 0.0){
            throw new IllegalArgumentException("Employee salary can't be 0");
        }

        Employee updatedEmployee = employeeOptional.get();
        updatedEmployee.setEmployeeName(employeeDTO.getEmployeeName());
        updatedEmployee.setAddress(employeeDTO.getAddress());
        updatedEmployee.setDepartment(employeeDTO.getDepartment());
        updatedEmployee.setSalary(employeeDTO.getSalary());
        updatedEmployee.setEmployeeMobiles(employeeDTO.getEmployeeMobiles());
        updatedEmployee.setEmails(employeeDTO.getEmails());

        return employeeRepository.save(updatedEmployee);
    }
}
