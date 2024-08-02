package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.EmployeesByDepartmentService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class EmployeesByDepartmentServiceImpl implements EmployeesByDepartmentService {

    private final EmployeeRepository employeeRepository;

    public EmployeesByDepartmentServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getEmployeeByDepartment(String department) {

        List<Employee> employees = employeeRepository.findByDepartment(department);

         return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary))
                 .map(this::convertToDTO)
                .toList();
    }

    private EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO
                .builder()
                .employeeId(employee.getEmployeeId())
                .employeeName(employee.getEmployeeName())
                .address(employee.getAddress())
                .salary(employee.getSalary())
                .department(employee.getDepartment())
                .emails(employee.getEmails())
                .employeeMobiles(employee.getEmployeeMobiles())
                .build();
    }
}
