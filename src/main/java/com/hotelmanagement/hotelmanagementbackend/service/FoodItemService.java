package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.FoodItem;
import com.hotelmanagement.hotelmanagementbackend.repository.FoodItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodItem addFoodItem(FoodItem item) {
        return foodItemRepository.save(item);
    }

    public List<FoodItem> getAllFoodItems() {
        return foodItemRepository.findAll();
    }

    public Optional<FoodItem> getFoodItemById(String id) {
        return foodItemRepository.findById(id);
    }

    public FoodItem updateFoodItem(String id, FoodItem updatedItem) {
        updatedItem.setId(id);
        return foodItemRepository.save(updatedItem);
    }

    public void deleteFoodItem(String id) {
        foodItemRepository.deleteById(id);
    }
}