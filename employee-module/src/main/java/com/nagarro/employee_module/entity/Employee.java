package com.nagarro.employee_module.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;

    @NotBlank
    private String employeeName;
    private String address;
    @NotBlank
    private String department;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    private Set<MobileNumber> employeeMobiles = new HashSet<>();

}
