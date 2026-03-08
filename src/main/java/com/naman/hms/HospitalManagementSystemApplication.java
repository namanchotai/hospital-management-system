package com.naman.hms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HospitalManagementSystemApplication {
	public static void main(String[] args) {
        System.out.println("Application Context Started");
        SpringApplication.run(HospitalManagementSystemApplication.class, args);
	}
}
