package com.nagarro.employee_module.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
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
    private double salary;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    @NotNull
    @NotEmpty
    private Set<Email> emails = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "employeeId")
    @NotNull
    @NotEmpty
    private Set<MobileNumber> employeeMobiles = new HashSet<>();

    private LocalDate date;

}
