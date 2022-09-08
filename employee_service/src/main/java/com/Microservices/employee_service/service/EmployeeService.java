package com.Microservices.employee_service.service;

import java.util.List;

import com.Microservices.employee_service.model.Employee;



public interface EmployeeService {
	
	public List<Employee> getEmployees();
	public Employee saveEmployee(Employee e);
	public Employee getEmployee(Long id);
	public void deleteEmployee(Long id);
	public Employee updateEmployee(Employee emp);
	public List<Employee> getEmployeesbyNameandLocation(String name, String location);
	public List<Employee> findAllFilter(boolean isDeleted);
}
