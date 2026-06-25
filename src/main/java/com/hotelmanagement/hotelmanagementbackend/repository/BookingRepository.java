package com.hotelmanagement.hotelmanagementbackend.repository;

import com.hotelmanagement.hotelmanagementbackend.model.Booking;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends MongoRepository<Booking, String> {

    // Get all bookings for a specific room (needed for availability check)
    List<Booking> findByRoomId(String roomId);

    // Get all bookings made by a specific user
    List<Booking> findByUserId(String userId);
}
