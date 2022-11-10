package com.kdev.hrm.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.kdev.hrm.entity.Employee;
import com.kdev.hrm.repo.EmployeeRepository;
import com.kdev.hrm.util.EmployeeNotFoundException;

@RestController
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@GetMapping("/employees")
    public @ResponseBody List<Employee> retrieveAllEmployees() {
        return employeeRepository.findAll();
    }
	
	@GetMapping("/employees/{id}")
    public Employee retrieveEmployee(@PathVariable long id) {
        Optional<Employee> employee = employeeRepository.findById(id);

        if (employee.isEmpty())
            throw new EmployeeNotFoundException("id-" + id);

        return employee.get();
    }
	
	@DeleteMapping("/employees/{id}")
    public void deleteEmployees(@PathVariable long id) {
		employeeRepository.deleteById(id);
    }

    @PostMapping("/employees")
    public Employee createStudent(@RequestBody Employee employee) {
    	Employee savedEmployee = employeeRepository.save(employee);

        return savedEmployee;

    }

    @PutMapping("/employees/{id}")
    public Employee updateEmployee(@RequestBody Employee employee, @PathVariable long id) {

        Optional<Employee> studentOptional = employeeRepository.findById(id);

        if (studentOptional.isEmpty())
            return null;

        employee.setId(id);

        return employeeRepository.save(employee);

    }


}
