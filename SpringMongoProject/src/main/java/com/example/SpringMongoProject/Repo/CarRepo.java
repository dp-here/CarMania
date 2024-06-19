package com.example.SpringMongoProject.Repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringMongoProject.Entity.Car;
@Repository
public interface CarRepo extends MongoRepository<Car, String>{

	Page<Car> findByNameIgnoreCaseContaining(String name, Pageable pageable);
    
    Page<Car> findByOriginIgnoreCaseContaining(String origin, Pageable pageable);
    
    Page<Car> findByNameIgnoreCaseContainingAndOriginIgnoreCaseContaining(String name, String origin, Pageable pageable);
	
}
