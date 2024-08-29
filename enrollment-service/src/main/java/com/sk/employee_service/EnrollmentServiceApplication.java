package com.sk.employee_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@OpenAPIDefinition(
		info = @Info(
				title = "Enrollment Microservice",
				description = "Enrollment Microservice for UKG Training",
				summary = "Enrollment CRUD Operations",
				contact = @Contact(
						name = "Sumit Khandelwal",
						email = "synergy@test.com",
						url = "www.example.com"
				)
		)
)
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
public class EnrollmentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EnrollmentServiceApplication.class, args);
	}

}
