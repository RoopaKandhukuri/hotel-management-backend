package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.FoodItem;
import com.hotelmanagement.hotelmanagementbackend.model.FoodOrder;
import com.hotelmanagement.hotelmanagementbackend.repository.FoodItemRepository;
import com.hotelmanagement.hotelmanagementbackend.repository.FoodOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodOrderService {

    @Autowired
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    public FoodOrder placeOrder(FoodOrder order) {

        double total = 0.0;

        for (String foodItemId : order.getFoodItemIds()) {
            Optional<FoodItem> itemOpt = foodItemRepository.findById(foodItemId);
            if (itemOpt.isEmpty()) {
                throw new RuntimeException("Food item not found: " + foodItemId);
            }
            total += itemOpt.get().getPrice();
        }

        order.setTotalAmount(total);
        order.setStatus("PENDING");

        return foodOrderRepository.save(order);
    }

    public List<FoodOrder> getOrdersByBooking(String bookingId) {
        return foodOrderRepository.findByBookingId(bookingId);
    }

    public FoodOrder updateStatus(String orderId, String newStatus) {
        Optional<FoodOrder> orderOpt = foodOrderRepository.findById(orderId);
        if (orderOpt.isEmpty()) {
            throw new RuntimeException("Order not found");
        }
        FoodOrder order = orderOpt.get();
        order.setStatus(newStatus);
        return foodOrderRepository.save(order);
    }
}
