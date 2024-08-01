package com.nagarro.employee_module.dao;

import com.nagarro.employee_module.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Integer> {
}
