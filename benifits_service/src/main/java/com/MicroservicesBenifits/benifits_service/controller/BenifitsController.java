package com.MicroservicesBenifits.benifits_service.controller;

import java.util.ArrayList;
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

import com.MicroservicesBenifits.benifits_service.converter.BenifitsConvertor;
import com.MicroservicesBenifits.benifits_service.dto.BenifitsDTO;
import com.MicroservicesBenifits.benifits_service.model.Benifits;
import com.MicroservicesBenifits.benifits_service.service.BenifitsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value = "Benifits Controller")
public class BenifitsController {
	
	
	@Autowired
	private BenifitsService bService; //as our application starts employeeservice will be injected to our Spring container

//		@Autowired
		//private BasicEmployeeService eService;
		
		
	@Autowired
	private BenifitsConvertor convert;
	
//	@Autowired
//	private Convertor convert2;
	
	@GetMapping("/benifits")  //--->@RequestMapping(--)
	@ApiOperation(value = "GET LIST OF ALL BENEFITS")
	public List<BenifitsDTO> getBenifits()
	{
		List<Benifits> b = bService.getBenifits();
		List<BenifitsDTO> dto = convert.entityToDto(b);
		return dto;
	} 
	
	@GetMapping("/benifits/{id}") //PathVariable
	@ApiOperation(value = "GET BENEFITS WITH THE HELP OF ID")
	public ResponseEntity<BenifitsDTO> getBenifit(@PathVariable("id") Long id)
	{
		return new ResponseEntity<BenifitsDTO>(convert.entityToDto(bService.getbenifit(id)), HttpStatus.OK);
		//return convert.entityToDto(bService.getbenifit(id));
	}
	
/*	@PutMapping("/benifits/{id}")
	@ApiOperation(value = "UPDATE BENEFITS")
	public BenifitsDTO updateBenifit(@PathVariable Long id, @Valid @RequestBody BenifitsDTO dto)
	{
		System.out.println("Updating the Benifits Data for  ID : " + id);
		dto.setId(id);
		
		Benifits b = new Benifits();
		b = convert.dtoToEntity(dto);
		
	
		return convert.entityToDto(bService.updateBenifit(b));
		
	} */
	
	@PostMapping("/benifits")
	@ApiOperation(value = "SAVE BENEFITS")
	public BenifitsDTO saveBenifit(@Valid  @RequestBody BenifitsDTO dto)
	{
		Benifits b = new Benifits();
		b = convert.dtoToEntity(dto);

		return convert.entityToDto(bService.saveBenefit(b));
	}
	
	//localhost:8080/employees?id=24
/*	@DeleteMapping("/benifits") //dealing with requestParams
	@ApiOperation(value = "DELETE BENIFTS")
	public ResponseEntity<DeleteDTO> deleteEmployee(@RequestParam("id") Long id) //if the requestparam parameter name is same is Long variable then we do not have to specify paramter in requestparam
	{
		bService.deleteBenifit(id);
		return new ResponseEntity<DeleteDTO>(new DeleteDTO("Benefits are deleted", id),  HttpStatus.OK);
	}
	
	@GetMapping("/getEmployeesByBenifits")
	@ApiOperation(value = "GET ALL EMPLOYEES WITH GIVEN BENIFITS ID")
	public List<EmployeeDTO> getEmployees(@RequestParam("id") Long id)
	{
		List<Employee> emp = bService.getEmployees(id);
		List<EmployeeDTO> dto = convert2.entityToDto(emp);
		
		return dto;
	} */
	
}



