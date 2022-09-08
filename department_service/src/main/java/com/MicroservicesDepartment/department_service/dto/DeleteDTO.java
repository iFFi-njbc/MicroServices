package com.MicroservicesDepartment.department_service.dto;

public class DeleteDTO {

	private String message;
	private Long id;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "DeleteDTO [message=" + message + ", id=" + id + "]";
	}
	public DeleteDTO(String message, Long id) {
		super();
		this.message = message;
		this.id = id;
	}
	public DeleteDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
