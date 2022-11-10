package com.kdev.hrm;

import java.net.MalformedURLException;
import java.net.URL;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import com.kdev.hrm.entity.Employee;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest {
	
	// bind the above RANDOM_PORT
    @LocalServerPort
    private int port;
    
    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void testCreateReadDelete() throws MalformedURLException {
    	
    	String url=new URL("http://localhost:" + port + "/employees").toString();
    	
    	Employee emp1 = new Employee((long) 1, "Chaitanya", "Birati", "PM", "IT");
    	ResponseEntity<Employee> entity1 = restTemplate.postForEntity(url, emp1, Employee.class);
        	
    	ResponseEntity<Employee[]> responseEntity = restTemplate.getForEntity(url, Employee[].class);
    	Employee[] employees = responseEntity.getBody();
        Assertions.assertThat(employees).extracting(Employee::getName).contains("Chaitanya");
        
        restTemplate.delete(url + "/" + entity1.getBody().getId());
        ResponseEntity<Employee[]> responseEntityNext = restTemplate.getForEntity(url, Employee[].class);
    	Employee[] employeesAfter = responseEntityNext.getBody();
        Assertions.assertThat(employeesAfter).isEmpty();
    	
    	
    	
    }

}
