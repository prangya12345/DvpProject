package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.EntityData;

@Repository
public interface DvpRepository extends JpaRepository<EntityData,String> {

	

}
