package com.hotelmanagement.hotelmanagementbackend.repository;

import com.hotelmanagement.hotelmanagementbackend.model.FoodItem;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FoodItemRepository extends MongoRepository<FoodItem, String> {
}
