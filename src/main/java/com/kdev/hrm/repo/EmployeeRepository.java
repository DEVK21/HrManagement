package com.kdev.hrm.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kdev.hrm.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
