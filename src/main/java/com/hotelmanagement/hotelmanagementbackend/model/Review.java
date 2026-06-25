package com.hotelmanagement.hotelmanagementbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Document(collection = "reviews")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    private String id;

    private String userId;     // who wrote the review

    private String roomId;     // which room it's about

    private int rating;        // 1 to 5

    private String comment;
}
