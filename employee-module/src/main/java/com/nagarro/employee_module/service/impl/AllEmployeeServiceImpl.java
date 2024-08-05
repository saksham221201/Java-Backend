package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.exception.BadRequestException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.AllEmployeesService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AllEmployeeServiceImpl implements AllEmployeesService {

    private final EmployeeRepository employeeRepository;

    public AllEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        if (employees.isEmpty()) {
            throw new BadRequestException("There are no employees in the database", HttpStatus.BAD_REQUEST.value());
        }
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO
                .builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .address(employee.getAddress())
                .emails(employee.getEmails())
                .salary(employee.getSalary())
                .employeeMobiles(employee.getEmployeeMobiles())
                .department(employee.getDepartment())
                .build();
    }
}
