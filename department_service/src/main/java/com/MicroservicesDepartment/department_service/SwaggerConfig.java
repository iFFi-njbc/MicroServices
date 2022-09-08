package com.MicroservicesDepartment.department_service;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket apiDocket()
	{
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
	            .apis(RequestHandlerSelectors.basePackage("com.MicroservicesDepartment.department_service"))
	            .paths(PathSelectors.any())
	            .build()
	            .apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
	    return new ApiInfo(
	            "EMPLOYEE MANAGEMENT SYTEM API for DEPARTMENTS",
	            "Api Reference for Developers",
	            "2.7",
	            "TERMS OF SERVICE URL",
	            new Contact("Ifrah","https://EMS_API.com","ifrahmasood2508@gmail.com"),
	            "LICENSE",
	            "LICENSE URL",
	            Collections.emptyList()
	    );


	}
	

	
}




















/*	return new ApiInfoBuilder().title("EMPLOYEE MANAGEMENT SYSTEM API")
.description("API reference for developers")
.termsOfServiceUrl("http://EMS_API.com")
.licenseUrl("License URL").version("2.7").build(); */
