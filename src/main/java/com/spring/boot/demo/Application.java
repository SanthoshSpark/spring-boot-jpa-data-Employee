package com.spring.boot.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import com.github.javafaker.Faker;
import com.spring.boot.demo.model.Address;
import com.spring.boot.demo.model.Employee;
import com.spring.boot.demo.repository.EmployeeRepository;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private EmployeeRepository  employeeRepository;
	
	private Faker faker = new Faker();

	@Override
	public void run(String... args) throws Exception {
		
		Stream<Employee> employees = IntStream.range(1, 1000)
				.mapToObj(i -> new Employee(
						        faker.name().firstName(),
						        faker.name().lastName(),
						        faker.phoneNumber().cellPhone(),
						        faker.internet().emailAddress(),
						        new Address(
						        		faker.address().streetAddress(),
						        		faker.address().city(),
						        		faker.address().state(),
						        		faker.address().zipCode())));
		
		List<Employee> employeeList = new ArrayList<>();
		employees.forEach(emp -> {
			Employee e = (Employee)emp;
			employeeList.add(e);
		});
		
		((Object) employeeRepository).saveAll(employeeList);
		
		int i = 0;
		
		while(i<=9) {
			System.out.println("\n\nThis Page : "+(i+1));
			int packNo = i++;
			int pageSize = 10;
			
			Pageable pageable = PageRequest.of(packNo, pageSize);
			
			Page<Employee> page = employeeRepository.findAll(pageable);
			
			List<Employee> employeesPage = page.getContent();
			
			employeesPage.forEach((e) ->{
	            System.out.println(e);
	        });
			
			int totalPage = page.getTotalPages();
			
			long totalElements = page.getTotalElements();
			
			int numberOfElements = page.getNumberOfElements();
			
			int size = page.getSize();
			
			boolean isLast = page.isLast();
			
		    boolean isFirst = page.isFirst();
		    
		    System.out.println("total page -> " + totalPage);
	        System.out.println("totalElements -> " + totalElements);
	        System.out.println("numberOfElements -> " + numberOfElements);
	        System.out.println(" size ->" + size);
	        System.out.println(" isLast -> " + isLast);
	        System.out.println(" isFirst -> " + isFirst);
			
		}
		
	}

	
}
