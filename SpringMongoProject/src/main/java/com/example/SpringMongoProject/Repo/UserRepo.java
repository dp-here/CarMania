package com.example.SpringMongoProject.Repo;

import java.util.Optional;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.SpringMongoProject.Entity.UserEntity;

@Repository
public interface UserRepo extends MongoRepository<UserEntity, String> {
	Optional<UserEntity> findByEmail(String email);
}
