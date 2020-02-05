package com.mentorondemand;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class MentorOnDemandApplication extends SpringBootServletInitializer {

	protected SpringApplicationBuilder configure(SpringApplicationBuilder application)
	{
		return application.sources(MentorOnDemandApplication.class);
	}
	
	public static void main(String[] args) {
		SpringApplication.run(MentorOnDemandApplication.class, args);
		
		System.out.println("Server is running...");
	}
}
