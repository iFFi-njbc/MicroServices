package com.MicroservicesDepartment.department_service.service;

import java.util.List;

import com.MicroservicesDepartment.department_service.model.Department;




public interface DepartmentService {
	
	public List<Department> getDepartments();
	public Department saveDepartment(Department d);
	public Department getDepartment(Long id);
	public void deleteDepartment(Long id);
	public Department updateDepartment(Department d);
	
	//public List<Employee> getEmployees(Long id);
}
