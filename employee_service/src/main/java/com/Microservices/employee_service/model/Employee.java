package com.Microservices.employee_service.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.NonNull;



//@Getter
//@Setter
@Component
@Entity   //by this this class now represents entity Table in our database application
@Table(name = "employee_service")
@SQLDelete(sql = "UPDATE employee_service SET Delete_Flag=true WHERE id=?")
@FilterDef(
        name = "deletedEmployeeFilter",
        parameters = @ParamDef(name = "isDeleted", type = "boolean")
)
@Filter(
        name = "deletedEmployeeFilter",
        condition = "Delete_Flag = :isDeleted"
)


public class Employee {
	
	//@JsonProperty("fullname")
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Column(name = "name")
	private String name;
	//@JsonIgnore
	
	@Column(name = "age")
	private Long age;
	@Column(name = "location")
	private String location;
	@Column(name = "email")
	private String email;

//	@ManyToOne
//	@JoinColumn(name = "department")
//	@JsonManagedReference
	private Long department; 
	
//	@ManyToOne
//	@JoinColumn(name = "benefits_id")
//	@JsonManagedReference
	private Long benifits;
	

	@ManyToOne
	@JoinColumn(name = "manager")
	private Employee manager;
	
	@Column(name = "Delete_Flag")
	private Boolean deleted = Boolean.FALSE;

	

	public Long getDepartment() {
		return department;
	}
	public void setDepartment(Long department) {
		this.department = department;
	}

	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAge() {
		return age;
	}
	public void setAge(Long age) {
		this.age = age;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Employee getManager() {
		return manager;
	}
	public void setManager(Employee manager) {
		this.manager = manager;
	}



	
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", location=" + location + ", email=" + email
				+ ", department=" + department + ", benifits=" + benifits + ", manager=" + manager + ", deleted="
				+ deleted + "]";
	}
	
	
	
	
	public Employee(Long id, String name, Long age, String location, String email, Long department,
			Long benifits, Employee manager, Boolean deleted) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.location = location;
		this.email = email;
		this.department = department;
		this.benifits = benifits;
		this.manager = manager;
		this.deleted = deleted;
	}
	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getBenifits() {
		return benifits;
	}
	public void setBenifits(Long benifits) {
		this.benifits = benifits;
	}
	public Boolean getDeleted() {
		return deleted;
	}
	public void setDeleted(Boolean deleted) {
		this.deleted = deleted;
	}

	
}
