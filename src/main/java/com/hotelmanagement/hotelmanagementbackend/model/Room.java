package com.hotelmanagement.hotelmanagementbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "rooms")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    private String id;

    private String roomNumber;

    private String roomType;       // e.g., Deluxe, Standard, Suite

    private double pricePerNight;

    private int capacity;          // max number of guests

    private boolean isAvailable;

    private String[] amenities;    // e.g., {"AC", "WiFi", "TV"}
}