package com.MicroservicesBenifits.benifits_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MicroservicesBenifits.benifits_service.exception.BuisnessException;
import com.MicroservicesBenifits.benifits_service.model.Benifits;
import com.MicroservicesBenifits.benifits_service.repository.BenifitsRepository;



@Service
public class BasicBenefitsService implements BenifitsService {
	
	
	 @Autowired
	private BenifitsRepository brepo; 
	 
	// @Autowired
	// private BasicEmployeeService eService;

	@Override
	public Benifits getbenifit(Long id) {
		
		if(id == null || id == 0)
		{
			throw new BuisnessException("705", "Enter Benifits ID");
		}
		
		Optional<Benifits> b = brepo.findById(id);
		if(b.isPresent())
		{
			return b.get();
		}
		else
			throw new RuntimeException("Benifits not found for the ID : " + id);
	}

	@Override
	public List<Benifits> getBenifits() {
		return brepo.findAll();
	}

	@Override
	public Benifits saveBenefit(Benifits b) {
		
		/*if(b.getId() == null)
		{
			throw new BenifitsException("700", "Enter Benifits ID");
		}*/
		if(b.getHealthcare().isEmpty() || b.getHealthcare().length() == 0)
		{
			throw new BuisnessException("701", "Missing Benifits {Healthcare}");
		}
		if(b.getLaptop().isEmpty() || b.getLaptop().length() == 0)
		{
			throw new BuisnessException("702", "Missing Benifits {laptop}");
		}
		if(b.getRetirementPlan().isEmpty() || b.getRetirementPlan().length() == 0)
		{
			throw new BuisnessException("703", "Missing Benifits {retirementplan}");
		} 
		return brepo.save(b);
	}

	@Override
	public void deleteBenifit(Long id) {
		Optional<Benifits> b =  brepo.findById(id);
		if(b.isPresent())
		{
			brepo.deleteById(id);
		}
		else
			throw new BuisnessException("704", "Benifits are not found for the ID : " + id);
		
		//brepo.deleteById(id);
		
	}

	@Override
	public Benifits updateBenifit(Benifits b) {
		/*if(b.getId() == null)
		{
			throw new BenifitsException("706", "Enter Benifits ID");
		}*/
		if(b.getHealthcare().isEmpty() || b.getHealthcare().length() == 0)
		{
			throw new BuisnessException("707", "Missing Benifits {Healthcare}");
		}
		if(b.getLaptop().isEmpty() || b.getLaptop().length() == 0)
		{
			throw new BuisnessException("708", "Missing Benifits {laptop}");
		}
		if(b.getRetirementPlan().isEmpty() || b.getRetirementPlan().length() == 0)
		{
			throw new BuisnessException("709", "Missing Benifits {retirementplan}");
		} 
		return brepo.save(b);
	}

/*	@Override
	public List<Employee> getEmployees(Long id) {
		
		if(id == null)
		{
			throw new BuisnessException("710", "Enter Benifits ID to get its Employees");
		}
		
		List<Employee> emp = new ArrayList<Employee>();

		for(Employee e : eService.getEmployees())
		{
			if(e.getBenifits().getId().equals(id))
			{
				emp.add(e);
			}
		}
		return emp;
	}*/
	



}
