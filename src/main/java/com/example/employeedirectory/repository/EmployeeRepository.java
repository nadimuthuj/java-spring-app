package com.example.employeedirectory.repository;

import com.example.employeedirectory.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
}
