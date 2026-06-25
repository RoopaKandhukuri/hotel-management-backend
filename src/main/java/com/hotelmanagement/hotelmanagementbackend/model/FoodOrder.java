package com.hotelmanagement.hotelmanagementbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Document(collection = "foodorders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrder {

    @Id
    private String id;

    private String userId;          // who placed the order

    private String bookingId;       // which stay it belongs to

    private List<String> foodItemIds;   // list of ordered item ids

    private Double totalAmount;

    private String status;          // PENDING, PREPARING, DELIVERED, CANCELLED
}