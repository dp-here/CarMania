package com.example.SpringMongoProject.Service;


import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SpringMongoProject.Entity.UserEntity;
import com.example.SpringMongoProject.Repo.UserRepo;

@Service
public class UserService {
    private final UserRepo userRepository;

    public UserService(UserRepo userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserEntity> allUsers() {
        List<UserEntity> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);

        return users;
    }
}
