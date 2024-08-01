package com.nagarro.employee_module.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
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

    public Employee(int employeeId, String employeeName, String address, String department, Set<Email> emails, Set<MobileNumber> employeeMobiles) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.address = address;
        this.department = department;
        this.emails = emails;
        this.employeeMobiles = employeeMobiles;
    }

    public Set<Email> getEmails() {
        return emails;
    }

    public void setEmails(Set<Email> emails) {
        this.emails = emails;
    }

    public Set<MobileNumber> getEmployeeMobiles() {
        return employeeMobiles;
    }

    public void setEmployeeMobiles(Set<MobileNumber> employeeMobiles) {
        this.employeeMobiles = employeeMobiles;
    }

    public Employee() {
        super();
    }


    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}
