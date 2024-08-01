package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dao.EmployeeDao;
import com.nagarro.employee_module.dto.EmployeeResponseDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    @Override
    public List<EmployeeResponseDTO> getAllEmployees() {
        List<Employee> employees = employeeDao.findAll();
        return employees.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponseDTO getEmployeeById(int employeeId) {
        Optional<Employee> optionalEmployee = employeeDao.findById(employeeId);
        if (optionalEmployee.isEmpty()) {
            throw new RecordNotFoundException("Employee with id " + employeeId + " not found!", HttpStatus.NOT_FOUND.value());
        }
        return convertToDTO(optionalEmployee.get());
    }

    private EmployeeResponseDTO convertToDTO(Employee employee) {
        return EmployeeResponseDTO
                .builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .address(employee.getAddress())
                .emails(employee.getEmails())
                .employeeMobiles(employee.getEmployeeMobiles())
                .department(employee.getDepartment())
                .build();
    }
}
