package com.example.SpringMongoProject.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SpringMongoProject.Entity.UserEntity;
import com.example.SpringMongoProject.Repo.UserRepo;
import com.example.SpringMongoProject.dtos.LoginUserDto;
import com.example.SpringMongoProject.dtos.RegisterUserDto;

@Service
public class AuthenticationService {
    private final UserRepo userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
        UserRepo userRepository,
        AuthenticationManager authenticationManager,
        PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserEntity signup(RegisterUserDto input) {
	    var user = new UserEntity();
	    user.setFullName(input.getFullName());
	    user.setEmail(input.getEmail());
	    user.setPassword(passwordEncoder.encode(input.getPassword()));

	    return userRepository.save(user);
	}

    public UserEntity authenticate(LoginUserDto input) {
    	System.out.println("Auth2"+ input.getPassword());
    	try {
    		  authenticationManager.authenticate(
    		            new UsernamePasswordAuthenticationToken(
    		                input.getEmail(),
    		                input.getPassword()
    		            )
    		        );
    	}catch(Error e) {
    		System.out.print(" Dfadsf  "+ e);
    	}
      
        System.out.print(userRepository.findByEmail(input.getEmail()));
        return userRepository.findByEmail(input.getEmail()).orElseThrow();
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
