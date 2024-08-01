package com.nagarro.employee_module.dto;

import com.nagarro.employee_module.entity.Email;
import com.nagarro.employee_module.entity.MobileNumber;
import lombok.Builder;
import lombok.Data;

import java.util.Set;

@Data
@Builder
public class EmployeeDTO {
    private int employeeId;
    private String employeeName;
    private String address;
    private String department;
    private Set<String> emails;
    private Set<String> employeeMobiles;

}
