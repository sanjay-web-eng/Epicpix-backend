package com.epicpix.epicpix.repos;

import com.epicpix.epicpix.models.Images;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImageRepo extends MongoRepository<Images , String> {
}
