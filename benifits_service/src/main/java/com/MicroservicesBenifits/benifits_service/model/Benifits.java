package com.MicroservicesBenifits.benifits_service.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.annotations.ApiModel;

@Component
@Entity
@Table(name = "benifits")
public class Benifits {
	
	@Id
	@GeneratedValue()
	@Column(name = "id")
	
	private Long id;
	
	@Column(name = "RetirementPlan")
	private String retirementPlan;
	@Column(name = "laptop")
	private String laptop;
	@Column(name = "healthcare")
	private String healthcare;
	
//	@OneToMany(mappedBy = "benifits", fetch = FetchType.EAGER)
//	@JsonBackReference
//	private List<Employee> employees; 
	

	public Benifits() {
		super();
		// TODO Auto-generated constructor stub
	}








/*	public List<Employee> getEmployees() {
		return employees;
	}








	public void setEmployees(List<Employee> employees) {
		this.employees = employees;
	}
*/







	public Long getId() {
		return id;
	}



	public Benifits(Long id, String retirementPlan, String laptop, String healthcare) {
		super();
		this.id = id;
		this.retirementPlan = retirementPlan;
		this.laptop = laptop;
		this.healthcare = healthcare;
		//this.employees = employees;
	}

















	@Override
	public String toString() {
		return "Benifits [id=" + id + ", remote=" + retirementPlan + ", laptop=" + laptop + ", healthcare=" + healthcare + "]";
	}








	public void setId(Long id) {
		this.id = id;
	}
	public String getRetirementPlan() {
		return retirementPlan;
	}
	public void setRetirementPlan(String retirementPlan) {
		this.retirementPlan = retirementPlan;
	}
	public String getLaptop() {
		return laptop;
	}
	public void setLaptop(String laptop) {
		this.laptop = laptop;
	}
	public String getHealthcare() {
		return healthcare;
	}
	public void setHeathcare(String heathcare) {
		this.healthcare = heathcare;
	}

}
