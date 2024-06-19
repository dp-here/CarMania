package com.example.SpringMongoProject.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.SpringMongoProject.Entity.Car;
import com.example.SpringMongoProject.Repo.CarRepo;
import com.example.SpringMongoProject.Service.CarService;

import lombok.RequiredArgsConstructor;

@RestController
@CrossOrigin(origins ="*")
@RequestMapping("api/v1/cars")
@RequiredArgsConstructor
public class CarController {
	@Autowired
	private CarService carservice;
	private final CarRepo repo;
	
	@PostMapping(value="/addCar")
	private ResponseEntity<Car> addCar(@RequestBody Car cars) {
		Car savedCar = repo.save(new Car(cars.getId(), cars.getName(), cars.getOrigin(), cars.getPrice()));
		return  new ResponseEntity<>(savedCar, HttpStatus.CREATED);
		
	}

	@GetMapping(value= "/getAll")
	public List<Car> getCars(){
		return carservice.getAll();
	}
	
	@RequestMapping("/getById/{id}")
	public Optional<Car> getCarById(@PathVariable(name="id")String id){
		return carservice.getById(id);
	}
	
	 @GetMapping("/search")
	    public Page<Car> searchCars(
	            @RequestParam(name = "name", required = false) String name,
	            @RequestParam(name = "origin", required = false) String origin,
	            @RequestParam(name = "page", defaultValue = "0") int page,
	            @RequestParam(name = "size", defaultValue = "10") int size) {

	        Pageable pageable = PageRequest.of(page, size);

	        if (name != null && origin != null) {
	            return carservice.getCarsByNameAndOrigin(name, origin, pageable);
	        } else if (name != null) {
	           
	            return carservice.getCarsByName(name, pageable);
	        } else if (origin != null) {
	           
	            return carservice.getCarsByOrigin(origin, pageable);
	        } else {
	          
	            throw new IllegalArgumentException("You must provide at least one of 'name' or 'origin' parameters.");
	        }
	    }
	
	@PutMapping("/editById/{id}")
	public Car updateById(@RequestBody Car cars, @PathVariable(name="id") String id){ 
		cars.setId(id);
		carservice.saveOrUpdate(cars);
		return cars;
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<HttpStatus> deleteById(@PathVariable(name="id") String id) {
		carservice.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
