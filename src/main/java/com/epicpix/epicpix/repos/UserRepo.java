package com.epicpix.epicpix.repos;


import com.epicpix.epicpix.models.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepo extends MongoRepository<Users , String> {
     Optional<Users> findByUsername();
}
