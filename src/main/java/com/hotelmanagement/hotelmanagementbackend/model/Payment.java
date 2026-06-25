package com.hotelmanagement.hotelmanagementbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "payments")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Payment {

    @Id
    private String id;

    private String bookingId;     // reference to the Booking

    private Double amount;

    private String method;        // CARD, UPI, CASH

    private String status;        // PENDING, PAID, REFUNDED
}