package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dao.EmployeeDao;
import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.entity.MobileNumber;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDao;

    @Autowired
    public EmployeeServiceImpl(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

//    @Override
//    public List<EmployeeDTO> getAllEmployees() {
//        List<Employee> employees = employeeDao.findAll();
//        return employees.stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }

//    @Override
//    public EmployeeDTO getEmployeeById(int employeeId) {
//        Optional<Employee> optionalEmployee = employeeDao.findById(employeeId);
//        if (optionalEmployee.isEmpty()) {
//            throw new RecordNotFoundException("Employee with id " + employeeId + " not found!", HttpStatus.NOT_FOUND.value());
//        }
//        return convertToDTO(optionalEmployee.get());
//    }

//    private EmployeeDTO convertToDTO(Employee employee) {
//        return EmployeeDTO
//                .builder()
//                .employeeId(employee.getEmployeeId())
//                .employeeName(employee.getEmployeeName())
//                .address(employee.getAddress())
//                .emails(employee.getEmails())
//                .employeeMobiles(employee.getEmployeeMobiles())
//                .department(employee.getDepartment())
//                .build();
//    }


    @Override
    public Employee createEmployee(EmployeeDTO employeeDTO) {

        Set<MobileNumber> mobileNumbers = employeeDTO.getEmployeeMobiles().stream()
                .map(n->MobileNumber.builder()
                        .number(n)
                        .build())
                .collect(Collectors.toSet());

        Set<Email> emails = employeeDTO.getEmails().stream()
                .map(e->Email.builder()
                        .employeeEmail(e)
                        .build())
                .collect(Collectors.toSet());

        Employee employee = Employee.builder()
                .employeeName(employeeDTO.getEmployeeName())
                .employeeMobiles(mobileNumbers)
                .emails(emails)
                .address(employeeDTO.getAddress())
                .department(employeeDTO.getDepartment())
                .build();

        return employeeDao.save(employee);
    }
}
