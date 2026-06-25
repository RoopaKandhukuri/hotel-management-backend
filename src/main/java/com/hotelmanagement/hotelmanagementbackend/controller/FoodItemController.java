package com.hotelmanagement.hotelmanagementbackend.controller;

import com.hotelmanagement.hotelmanagementbackend.model.FoodItem;
import com.hotelmanagement.hotelmanagementbackend.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/fooditems")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public FoodItem addFoodItem(@RequestBody FoodItem item) {
        return foodItemService.addFoodItem(item);
    }

    @GetMapping
    public List<FoodItem> getAllFoodItems() {
        return foodItemService.getAllFoodItems();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodItem> getFoodItemById(@PathVariable String id) {
        Optional<FoodItem> item = foodItemService.getFoodItemById(id);
        return item.map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public FoodItem updateFoodItem(@PathVariable String id, @RequestBody FoodItem item) {
        return foodItemService.updateFoodItem(id, item);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFoodItem(@PathVariable String id) {
        foodItemService.deleteFoodItem(id);
        return ResponseEntity.ok("Food item deleted successfully");
    }
}