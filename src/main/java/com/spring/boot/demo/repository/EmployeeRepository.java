package com.spring.boot.demo.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.spring.boot.demo.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Integer>{

}
