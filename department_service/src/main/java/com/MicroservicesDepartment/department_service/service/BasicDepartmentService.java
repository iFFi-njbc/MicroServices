package com.MicroservicesDepartment.department_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MicroservicesDepartment.department_service.exception.BuisnessException;
import com.MicroservicesDepartment.department_service.model.Department;
import com.MicroservicesDepartment.department_service.repository.DepartmentRepository;



@Service
public class BasicDepartmentService implements DepartmentService {
	
	
	 @Autowired
	private DepartmentRepository drepo; 
	 
//	 @Autowired
	// private EmployeeService eService;
	

	@Override
	public List<Department> getDepartments() {
		
		return drepo.findAll();

	}

	@Override
	public Department saveDepartment(Department d) {
		/*if(d.getId() == null)
		{
			throw new DepartmentException("800", "Enter Department ID");
		}*/
		if(d.getName().isEmpty()  || d.getName().length() == 0)
		{
			throw new BuisnessException("801", "Enter proper Department name");
		}
		
		return drepo.save(d);
	}

	@Override
	public Department getDepartment(Long id) {
		
		if(id == null || id == 0)
		{
			throw new BuisnessException("802", "Enter Departments ID");
		}
		
		Optional<Department> d =  drepo.findById(id);
		if(d.isPresent())
		{
			return d.get();
		}
		else
			throw new RuntimeException("Department is not found for the ID : " + id);
	}

	@Override
	public void deleteDepartment(Long id) {
		
		Optional<Department> d =  drepo.findById(id);
		if(d.isPresent())
		{
			drepo.deleteById(id);
		}
		else
			throw new BuisnessException("803", "Department are not found for the ID : " + id);
		
		//drepo.deleteById(id);

	}

	@Override
	public Department updateDepartment(Department d) {
		/*if(d.getId() == null)
		{
			throw new DepartmentException("800", "Enter Department ID");
		}*/
		if(d.getName().isEmpty()  || d.getName().length() == 0)
		{
			throw new BuisnessException("801", "Enter proper Department name");
		}
		return drepo.save(d);
	}
	
/*	public List<Employee> getEmployees(Long id)
	{
		List<Employee> emp = new ArrayList<Employee>();

		for(Employee e : eService.getEmployees())
		{
			if(e.getDepartment().getId().equals(id))
			{
				emp.add(e);
			}
		}
		
		return emp;
	} */

}
