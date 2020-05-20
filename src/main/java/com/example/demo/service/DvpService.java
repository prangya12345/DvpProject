package com.example.demo.service;

import com.example.demo.Exception.RecordNotFoundException;
import com.example.demo.model.EntityData;

public interface  DvpService {
	public EntityData getElectricityDetails(String id);

}


