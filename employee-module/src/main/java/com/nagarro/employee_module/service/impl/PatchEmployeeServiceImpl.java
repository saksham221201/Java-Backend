package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.PatchEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PatchEmployeeServiceImpl implements PatchEmployeeService {
    Logger logger = LoggerFactory.getLogger(PatchEmployeeServiceImpl.class);

    private final EmployeeRepository employeeRepository;

    public PatchEmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeDTO patchEmployee(int employeeId, Map<String,Object> patches) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        if(optionalEmployee.isEmpty()){
            throw new RecordNotFoundException("Employee with id " + employeeId + " not found!", HttpStatus.NOT_FOUND.value());
        }

        Employee emp = optionalEmployee.get();

        patches.forEach((key,value)->{
            switch (key){
                case "emails":
                    if(value instanceof List){
                        List<Map<String,Object>> emailList = (List<Map<String,Object>>) value;
                        Set<Email> patchedEmails = emailList.stream()
                                .map(emailMap->{
                                    Integer emailId = Integer.parseInt(emailMap.get("id").toString());
                                    String employeeEmails = (String) emailMap.get("employeeEmail");
                                    Optional<Email> currEmail = emp.getEmails().stream()
                                            .findFirst();
                                    if (currEmail.isPresent()){
                                        currEmail.get().setEmployeeEmail(employeeEmails);
                                        return currEmail.get();
                                    }
                                    return new Email(employeeEmails);
                                }).collect(Collectors.toSet());

                        emp.setEmails(patchedEmails);
                    }
                    break;
                default:
                    Field field = ReflectionUtils.findField(Employee.class,key);
                    field.setAccessible(true);
                    ReflectionUtils.setField(field,emp,value);
                    break;
            }
        });

        Employee patchedEmployee = employeeRepository.save(emp);
        return convertToDTO(patchedEmployee);

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
