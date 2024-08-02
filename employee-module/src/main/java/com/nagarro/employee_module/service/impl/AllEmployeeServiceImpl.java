package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.service.AllEmployeesService;
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
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        // Empty List Check
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
