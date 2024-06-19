package com.example.SpringMongoProject.Entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.example.SpringMongoProject.dtos.RegisterUserDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="userDB")
public class UserEntity {

	@Id
	private String id;
	private String username;
	private String fullName;
	private String password;
	private String email;
	
//	 public UserEntity setEmail(String email) {
//	        this.email = email;
//	        return this;
//	    }

}