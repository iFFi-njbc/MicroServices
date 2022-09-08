package com.MicroservicesBenifits.benifits_service.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.MicroservicesBenifits.benifits_service.dto.BenifitsDTO;
import com.MicroservicesBenifits.benifits_service.model.Benifits;

@Component
public class BenifitsConvertor {
	
	public BenifitsDTO entityToDto(Benifits b)
	{
		BenifitsDTO dto = new BenifitsDTO();
		dto.setId(b.getId());
		dto.setHealthcare(b.getHealthcare());
		dto.setLaptop(b.getLaptop());
		dto.setRetirementPlan(b.getRetirementPlan());
		
		return dto;
	}
	
	
	public List<BenifitsDTO> entityToDto(List<Benifits> b)
	{
		return (List<BenifitsDTO>)b.stream().map(this::entityToDto).collect(Collectors.toList());
	}
	
	
	
	
	public Benifits dtoToEntity(BenifitsDTO dto)
	{
		Benifits b = new Benifits();

		

		b.setId(dto.getId());

		b.setHeathcare(dto.getHealthcare());
		b.setLaptop(dto.getLaptop());
		b.setRetirementPlan(dto.getRetirementPlan());
		return b;
	}
	
	
	public List<Benifits> dtoToEntity(List<BenifitsDTO> dto)
	{
		return (List<Benifits>)dto.stream().map(this::dtoToEntity).collect(Collectors.toList());
	}
	

}
