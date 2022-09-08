package com.Microservices.employee_service.controller;

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
import org.springframework.web.client.RestTemplate;

import com.Microservices.employee_service.converter.Convertor;
import com.Microservices.employee_service.dto.BenifitsDTO;
import com.Microservices.employee_service.dto.DeleteDTO;
import com.Microservices.employee_service.dto.DepartmentDTO;
import com.Microservices.employee_service.dto.EmployeeDTO;
import com.Microservices.employee_service.exception.ErrorDetails;
import com.Microservices.employee_service.model.Employee;
import com.Microservices.employee_service.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


@RestController //@controller + @ResponseBody
@Api(value = "Employee Controller")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService; //as our application starts employeeservice will be injected to our Spring container

	@Autowired
	private RestTemplate restTemplate;
	
	
	@Autowired
	private Convertor convert;
	
	@Autowired
	private DepartmentDTO d;
	@Autowired
	private Employee m;
	@Autowired
	private BenifitsDTO b;
	
//	@Autowired
//	private BasicBenefitsService bService;
	
	
//	@Autowired 
//	private DepartmentService dService; 

	

	
	//@RequestMapping(value="/employees", method = RequestMethod.GET)
	//@ResponseBody //for RestAPI
	
	@Value("${app.name}") //or ${app.name : Employee Management System}
	private String appName;
	
	@Value("${app.version}") // or ${app.version : version1}
	private String appVersion;
	
	
	@GetMapping("/version")
	@ApiOperation(value = "GET APPLICATION DETAILS")
	public String getAppDetails()
	{
		return appName + "\n" + appVersion;
	}
	
	
	@GetMapping("/employees")  //--->@RequestMapping(--)
	@ApiOperation(value = "GET LIST OF ALL EMPLOYEES")
	public ResponseEntity<List<EmployeeDTO>> getEmployees()
	{

		List<Employee> emp = empService.getEmployees();
		List<EmployeeDTO> dto = convert.entityToDto(emp);
		return new ResponseEntity<List<EmployeeDTO>>(dto, HttpStatus.OK);
	
	} 
	
	
	
	
	
	
	@GetMapping("/employees/{id}") //PathVariable
	@ApiOperation(value = "GET EMPLOYEE DETAILS WITH ID")
	public EmployeeDTO getEmployee(@PathVariable("id") Long id)
	{
		System.out.println(empService.getEmployee(id));
		return convert.entityToDto(empService.getEmployee(id));
		//return empService.getEmployee(id);
	}
	
	
	
	
	@PutMapping("/employees/{id}")
	@ApiOperation(value = "UPDATE EMPLOYEE DETAILS")
	public EmployeeDTO updateEmployee(@PathVariable Long id, @Valid @RequestBody EmployeeDTO dto)
	{
		System.out.println("Updating the Employee Data for Employee ID : " + id);
		dto.setId(id);
		
		Employee emp = new Employee();
		emp = convert.dtoToEntity(dto);
		
		//Benifits b = new Benifits();
		//Department d = new Department();
		//Employee m = new Employee();

		d = restTemplate.postForObject("http://localhost:9002/RESTapi/v1/departments/" + emp.getDepartment() , d, DepartmentDTO.class);
		//d = (Department)dService.getDepartment(emp.getDepartment().getId());
		if(emp.getManager().getId() != null)
		{
			m = (Employee)empService.getEmployee(emp.getManager().getId());
		}
		else
		{
			m = null;
		}

		b = restTemplate.postForObject("http://localhost:9003/RESTapi/v1/benifits/" + emp.getBenifits() , b, BenifitsDTO.class);
		//b = (Benifits)bService.getbenifit(emp.getBenifits().getId());
		emp.setBenifits(b.getId());
		emp.setDepartment(d.getId());
		emp.setManager(m);
		
		
		
		return convert.entityToDto(empService.updateEmployee(emp));
	} 
	
	
	
	
	
	
	@PostMapping("/employees")
	@ApiOperation(value = "CREATE A NEW EMPLOYEE")
	public ResponseEntity<?> saveEmployee(@Valid @RequestBody EmployeeDTO empreq)
	{
		
		Employee e = new Employee();
		e = convert.dtoToEntity(empreq);
		
		//Benifits b = new Benifits();
		//Department d = new Department();
		//Employee m = new Employee();
		try {
		 d = restTemplate.getForObject("http://localhost:9002/RESTapi/v1/departments/" + empreq.getDepartment(), DepartmentDTO.class);
		System.out.println(d);
		b = restTemplate.getForObject("http://localhost:9003/RESTapi/v1/benifits/" + empreq.getBenifits(), BenifitsDTO.class);
		System.out.println(b);
		}
		catch(RuntimeException ex)
		{
			return new ResponseEntity<ErrorDetails>(new ErrorDetails(ex.getMessage(), b.getId().toString()), HttpStatus.BAD_REQUEST);
		}
		//d = (Department)dService.getDepartment(e.getDepartment().getId());
		//m = (Manager)mService.getManager(empreq.getManager().getId());
		if(e.getManager().getId() != null)
		{
			m = (Employee)empService.getEmployee(e.getManager().getId());
		}
		else
		{
			m = null;
		}
		

		//b = (Benifits)bService.getbenifit(e.getBenifits().getId());
		System.out.println("------->" + e.getBenifits());
		//System.out.println("------->" + (Benifits)bService.getbenifit(e.getBenifits().getId()));
		System.out.println("------->" + b);
		e.setBenifits(b.getId());
		e.setDepartment(d.getId());
		e.setManager(m);
		
		//return empService.saveEmployee(empreq); 
		//System.out.println(e);

		
		return new ResponseEntity<EmployeeDTO>(convert.entityToDto(empService.saveEmployee(e)), HttpStatus.OK);

	}
	
	
	
	
	
	
	
	//localhost:8080/employees?id=24
	@DeleteMapping("/employees") //dealing with requestParams
	@ApiOperation(value = "DELETE AN EMPLOYEE")
	public ResponseEntity<DeleteDTO> deleteEmployee(@RequestParam("id") Long id) //if the requestparam parameter name is same is Long variable then we do not have to specify paramter in requestparam
	{

		empService.deleteEmployee(id);
		return new ResponseEntity<DeleteDTO>(new DeleteDTO("Employee is deleted", id),  HttpStatus.OK);
	}
	
	
	
	
	
	@GetMapping("/employees/getEmployeesByNameAndLocation")
	@ApiOperation(value = "GET EMPLOYEE BY NAME AND LOCATION")
	public ResponseEntity<List<EmployeeDTO>> getEmployeesByNameAndLocation(@RequestParam String name, @RequestParam String location)
	{
		return new ResponseEntity<List<EmployeeDTO>>(convert.entityToDto(empService.getEmployeesbyNameandLocation(name, location)), HttpStatus.OK);
	}
	
	
	@GetMapping(value = "/employeesByFilter")
	@ApiOperation(value = "GET DELETED EMPLOYEES BY SETTING VALUE OF 'isDeleted' TO TRUE")
	public ResponseEntity<List<EmployeeDTO>> findAll(
	@RequestParam(value = "isDeleted", defaultValue = "false") boolean isDeleted) {
	    List<Employee> emp = empService.findAllFilter(isDeleted);
		List<EmployeeDTO> dto = convert.entityToDto(emp);
		for(EmployeeDTO e : dto )
		{
			System.out.println(e);
		}
	    
	    return new ResponseEntity<List<EmployeeDTO>>(dto, HttpStatus.OK);
	} 
	
	@GetMapping(value = "/getEmployeeDepartmentDetails/{id}")
	@ApiOperation(value = "GETTING EMPLOYEES DEPARTMENT DETAILS")
	public ResponseEntity<DepartmentDTO> depDetails(@PathVariable("id") Long id)
	{
		System.out.println(empService.getEmployee(id));
		Employee e = empService.getEmployee(id);	
		DepartmentDTO dep = restTemplate.getForObject("http://localhost:9002/RESTapi/v1/departments/" + e.getDepartment(), DepartmentDTO.class);
		return new ResponseEntity<DepartmentDTO>(dep, HttpStatus.OK);
	}
	
}
