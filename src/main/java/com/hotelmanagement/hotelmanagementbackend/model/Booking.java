package com.hotelmanagement.hotelmanagementbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Document(collection = "bookings")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Booking {

    @Id
    private String id;

    private String userId;       // reference to User who booked

    private String roomId;       // reference to the booked Room

    private LocalDate checkInDate;

    private LocalDate checkOutDate;

    private String status;       // PENDING, CONFIRMED, CANCELLED

    private Double totalAmount;
}