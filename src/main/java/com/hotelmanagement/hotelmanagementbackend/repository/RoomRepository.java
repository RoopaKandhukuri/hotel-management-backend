package com.hotelmanagement.hotelmanagementbackend.repository;

import com.hotelmanagement.hotelmanagementbackend.model.Room;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends MongoRepository<Room, String> {

}