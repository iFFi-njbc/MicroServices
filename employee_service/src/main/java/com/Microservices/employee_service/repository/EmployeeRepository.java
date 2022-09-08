package com.Microservices.employee_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Microservices.employee_service.model.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByNameAndLocation(String name, String location);

}
