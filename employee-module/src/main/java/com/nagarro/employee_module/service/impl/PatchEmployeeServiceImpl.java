package com.nagarro.employee_module.service.impl;

import com.nagarro.employee_module.dto.EmployeeDTO;
import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.Employee;
import com.nagarro.employee_module.entity.MobileNumber;
import com.nagarro.employee_module.exception.RecordNotFoundException;
import com.nagarro.employee_module.repository.EmployeeRepository;
import com.nagarro.employee_module.service.PatchEmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.*;
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

                        Map<Integer,Email> existingEmails = emp.getEmails().stream()
                                .collect(Collectors.toMap(Email::getEmailId, e->e));

                        Set<Email> patchedEmails = emailList.stream()
                                .map(emailMap->{
                                    Integer emailId = Integer.parseInt(emailMap.get("emailId").toString());

                                    String employeeEmail = (String) emailMap.get("employeeEmail");
                                    if(emailId != null && employeeEmail != null){
                                        //if emailId already exists, update email
                                        Email currEmail = existingEmails.get(emailId);
                                        if (currEmail != null){
                                            currEmail.setEmployeeEmail(employeeEmail);
                                            return currEmail;
                                        }
                                        //if emailId doesnt exist, make a new one
                                        return new Email(emailId,employeeEmail);
                                    }
                                    return null;
                                }).filter(Objects::nonNull)
                                .collect(Collectors.toSet());

                        emp.setEmails(patchedEmails);
                    }
                    break;
                case "employeeMobiles":
                    if(value instanceof List){
                        List<Map<String,Object>> mobileList = (List<Map<String,Object>>) value;

                        Map<Integer, MobileNumber> existingMobiles = emp.getEmployeeMobiles().stream()
                                .collect(Collectors.toMap(MobileNumber::getMobileId, m->m));

                        Set<MobileNumber> patchedMobiles = mobileList.stream()
                                .map(mobileMap->{
                                    Integer mobileId = Integer.parseInt(mobileMap.get("mobileId").toString());

                                    String number = (String) mobileMap.get("number");
                                    if(mobileId != null && number != null){
                                        //if emailId already exists, update email
                                        MobileNumber currMobile = existingMobiles.get(mobileId);
                                        if (currMobile != null){
                                            currMobile.setNumber(number);
                                            return currMobile;
                                        }
                                        //if emailId doesnt exist, make a new one
                                        return new MobileNumber(mobileId,number);
                                    }
                                    return null;
                                }).filter(Objects::nonNull)
                                .collect(Collectors.toSet());

                        emp.setEmployeeMobiles(patchedMobiles);
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
