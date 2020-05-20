package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.service.DvpService;

@SpringBootApplication
public class DvpProjectApplication implements CommandLineRunner{
	@Autowired
	DvpService service;
	

	public static void main(String[] args) {
		SpringApplication.run(DvpProjectApplication.class, args);
		System.out.println("hel99999o");
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("hel999999999999999999999999999lo");
		//service.getElectricityDetails();
		
	}
	

}
