package com.nagarro.employee_module.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String employeeName;
    private String address;
    private String department;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private Set<MobileNumber> employeeMobiles = new HashSet<>();

}
