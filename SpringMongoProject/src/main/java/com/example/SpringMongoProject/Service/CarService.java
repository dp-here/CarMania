package com.example.SpringMongoProject.Service;

import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.SpringMongoProject.Entity.Car;
import com.example.SpringMongoProject.Repo.CarRepo;

@Service
public class CarService {
	
	@Autowired
	private CarRepo repo;
	
	public Car saveOrUpdate(Car cars) {
		return repo.save(cars);
	}

	public List<Car> getAll(){
		return repo.findAll();
	}
	
	public Optional<Car > getById(String id){
		return repo.findById(id);
	}
	
	public String deleteById(String id) {
		repo.deleteById(id);
		return id;
	}
	
	public Page<Car> getCarsByName(String name, Pageable pageable) {
        return repo.findByNameIgnoreCaseContaining(name, pageable);
    }

    public Page<Car> getCarsByOrigin(String origin, Pageable pageable) {
        return repo.findByOriginIgnoreCaseContaining(origin, pageable);
    }

    public Page<Car> getCarsByNameAndOrigin(String name, String origin, Pageable pageable) {
        return repo.findByNameIgnoreCaseContainingAndOriginIgnoreCaseContaining(name, origin, pageable);
    }
	

}
