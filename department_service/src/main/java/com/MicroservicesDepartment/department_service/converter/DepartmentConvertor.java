package com.MicroservicesDepartment.department_service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.MicroservicesDepartment.department_service.dto.DepartmentDTO;
import com.MicroservicesDepartment.department_service.model.Department;



@Component
public class DepartmentConvertor {
	public DepartmentDTO entityToDto(Department d)
	{
		DepartmentDTO dto = new DepartmentDTO();
		dto.setId(d.getId());
		dto.setName(d.getName());
		
		return dto;
	}
	
	
	public List<DepartmentDTO> entityToDto(List<Department> d)
	{
		return (List<DepartmentDTO>)d.stream().map(this::entityToDto).collect(Collectors.toList());
	}
	
	
	
	
	public Department dtoToEntity(DepartmentDTO dto)
	{
		Department d = new Department();

		

		d.setId(dto.getId());

		d.setName(dto.getName());
		return d;
	}
	
	
	public List<Department> dtoToEntity(List<DepartmentDTO> dto)
	{
		return (List<Department>)dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}
	

}
