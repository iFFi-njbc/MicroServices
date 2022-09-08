package com.MicroservicesDepartment.department_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MicroservicesDepartment.department_service.converter.DepartmentConvertor;
import com.MicroservicesDepartment.department_service.dto.DepartmentDTO;
import com.MicroservicesDepartment.department_service.model.Department;
import com.MicroservicesDepartment.department_service.service.DepartmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController
@Api(value = "Department Controller")
public class DepartmentController {
	
//	@Autowired
	//private EmployeeService empService; //as our application starts employeeservice will be injected to our Spring container

	@Autowired
	private DepartmentService dService;
	
	@Autowired
	private DepartmentConvertor convert; 
	

	

	
	@GetMapping("/departments")  //--->@RequestMapping(--)
	@ApiOperation(value = "GET LIST OF ALL DEPARTMENTS")
	public List<DepartmentDTO> getDepartments()
	{
		List<Department> d = dService.getDepartments();
		List<DepartmentDTO> dto = convert.entityToDto(d);
		return dto;
	} 
	
	@GetMapping("/departments/{id}") //PathVariable
	@ApiOperation(value = "GET DEPARTMENT WITH THE ID")
	public DepartmentDTO getDepartment(@PathVariable("id") Long id)
	{
		return convert.entityToDto(dService.getDepartment(id));
	}
	
/*	@PutMapping("/departments/{id}")
	@ApiOperation(value = "UPDATE A DEPARTMENT")
	public DepartmentDTO updateDepartment(@PathVariable Long id, @Valid @RequestBody DepartmentDTO dto)
	{
		
		System.out.println("Updating the Department for ID : " + id);
		dto.setId(id);
		
		Department d = new Department();
		d = convert.dtoToEntity(dto);
		
	
		return convert.entityToDto(dService.updateDepartment(d));
	} */
	
	@PostMapping("/departments")
	@ApiOperation(value = "CREATE A DEPARTMENT")
	public DepartmentDTO saveDepartment(@Valid @RequestBody DepartmentDTO dto)
	{
		Department d = new Department();
		d = convert.dtoToEntity(dto);

		return convert.entityToDto(dService.saveDepartment(d));
	}
	
	//localhost:8080/employees?id=24
/*	@DeleteMapping("/departments") //dealing with requestParams
	@ApiOperation(value = "DELETE A DEPARTMENT")
	public ResponseEntity<DeleteDTO> deleteEmployee(@RequestParam("id") Long id) //if the requestparam parameter name is same is Long variable then we do not have to specify paramter in requestparam
	{
		dService.deleteDepartment(id);
		return new ResponseEntity<DeleteDTO>(new DeleteDTO("Department is deleted", id),  HttpStatus.OK);
	}
	
	@GetMapping("departments/getEmployees")
	@ApiOperation(value = "GET LIST OF ALL EMPLOYEES WITH THE GIVEN DEPARTMENT ID")
	public List<EmployeeDTO> getEmployees(@RequestParam Long id)
	{
		List<Employee> emp = dService.getEmployees(id);
		List<EmployeeDTO> dto = convert2.entityToDto(emp);
		return dto;
	} */

}
