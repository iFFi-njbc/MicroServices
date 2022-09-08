package com.Microservices.employee_service.service;

import java.util.List;
import java.util.Optional;


import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Microservices.employee_service.converter.Convertor;
import com.Microservices.employee_service.exception.BuisnessException;
import com.Microservices.employee_service.model.Employee;
import com.Microservices.employee_service.repository.EmployeeRepository;



@Service
public class BasicEmployeeService implements EmployeeService{
	
	 @Autowired
	private EmployeeRepository empRepository; 
	

	 
	 @Autowired
	 private Convertor convert;
	 
	 @Autowired
	 private EntityManager entityManager;
	 

	
	@Override
	public List<Employee> getEmployees() {
		

			List<Employee> emp = empRepository.findAll();
			if(emp.isEmpty())
			{
				throw new BuisnessException("604", "Employee List is Empty we have nothing to show");
			}
		
		//List<Employee> emp = empRepository.findAll();
		//List<EmployeeDTO> dto = convert.entityToDto(emp);
		return empRepository.findAll();


	}

	@Override
	public Employee saveEmployee(Employee emp) {
		
			if(emp.getName().isEmpty() || emp.getName().length() == 0)
			{
				throw new BuisnessException("601", "Please Send proper Name of the Employee !!!");
			}
			if(emp.getAge() == null)
			{
				throw new BuisnessException("611", "Employee Age cannot be null !!!");
			}
			if(emp.getBenifits()== null)
			{
				throw new BuisnessException("622", "Employee's benefits ID is null");
			}
			//if(emp.getDepartment().getId() == null)
			if(emp.getDepartment() == null)
			{
				throw new BuisnessException("633", "Employee's department ID is null");
			}
			if(emp.getEmail().isEmpty() || emp.getEmail().length() == 0)
			{
				throw new BuisnessException("644", "Please Send proper Email of the Employee !!!");
			}
			if(emp.getLocation().isEmpty() || emp.getLocation().length() == 0)
			{
				throw new BuisnessException("655", "Please Send proper Location of the Employee !!!");
			}
		
		/*Employee e = new Employee();
		EmployeeDTO d = new EmployeeDTO();
		
		e = convert.dtoToEntity(dto);
		empRepository.save(e);
		d = convert.entityToDto(e);*/
	  return empRepository.save(emp);

		
	}



	@Override
	public Employee getEmployee(Long id) {
		
		Optional<Employee> emp =  empRepository.findById(id);
				
		if(emp.isPresent())
		{
			return emp.get();
		}
		else
			throw new RuntimeException("Employee is not found for the ID : " + id);
	}



	@Override
	public void deleteEmployee(Long id) {
		
		Optional<Employee> emp =  empRepository.findById(id);
		if(emp.isPresent())
		{
			empRepository.deleteById(id);
		}
		else
			throw new BuisnessException("606", "Employee is not found for the ID : " + id);
		
	}



	@Override
	public Employee updateEmployee(Employee emp) {

			if(emp.getName().isEmpty() || emp.getName().length() == 0)
			{
				throw new BuisnessException("601", "Please Send proper Name of the Employee !!!");
			}
			if(emp.getAge() == null)
			{
				throw new BuisnessException("611", "Employee Age is null !!!");
			}
			if(emp.getBenifits() == null)
			{
				throw new BuisnessException("622", "Employee's benefits ID is null");
			}
			if(emp.getDepartment() == null)
			{
				throw new BuisnessException("633", "Employee's department ID is null");
			}
			if(emp.getEmail().isEmpty() || emp.getEmail().length() == 0)
			{
				throw new BuisnessException("644", "Please Send proper Email of the Employee !!!");
			}
			if(emp.getLocation().isEmpty() || emp.getLocation().length() == 0)
			{
				throw new BuisnessException("655", "Please Send proper Location of the Employee !!!");
			}	
			
		
		return empRepository.save(emp);


	}


	@Override
	public List<Employee> getEmployeesbyNameandLocation(String name, String location) {
		
		if(name.isEmpty() || location.isEmpty())
		{
			throw new BuisnessException("607", "Enter Name and Location properly !!! ");
		}
		return empRepository.findByNameAndLocation(name, location);
	}
	
	
	
	public List<Employee> findAllFilter(boolean isDeleted) {
	    Session session = entityManager.unwrap(Session.class);
	    Filter filter = session.enableFilter("deletedEmployeeFilter");
	    filter.setParameter("isDeleted", isDeleted);
	    List<Employee> users = empRepository.findAll();
	    session.disableFilter("deletedEmployeeFilter");
	    return users;
	}
	
	
/*	public Employee dtoToEmp(EmployeeDTO dto)
	{
		Employee emp = this.modelMapper.map(dto, Employee.class);
		return emp;
	}
	
	public EmployeeDTO empToDto(Employee e)
	{
		EmployeeDTO dto = this.modelMapper.map(e, EmployeeDTO.class);
		return dto;
	} */

}
