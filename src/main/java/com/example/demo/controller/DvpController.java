package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Exception.RecordNotFoundException;

import com.example.demo.model.EntityData;
import com.example.demo.service.DvpService;

@RestController
@RequestMapping("/dvp")
public class DvpController {
	@Autowired
	DvpService dvpService;

	@GetMapping("/getDetails/{id}")
	public ResponseEntity<EntityData> getElectricityDetails(@PathVariable String id){
		EntityData  e=dvpService.getElectricityDetails(id);
		if(e==null) {
			throw new RecordNotFoundException("record not fffffffffffffffffff");
		}
		
		return new ResponseEntity<EntityData>(e,HttpStatus.OK);
		
	}
}


