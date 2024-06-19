package com.example.SpringMongoProject.Entity;

import org.springframework.data.annotation.Id;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection="cars")
public class Car {
	
	 @Id
	 private String id;
	 private String name;
	 private String origin;
	 private String price;
	
	
}

