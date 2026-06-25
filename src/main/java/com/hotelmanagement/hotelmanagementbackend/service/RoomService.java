package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.Room;
import com.hotelmanagement.hotelmanagementbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Create a new room
    public Room addRoom(Room room) {
        return roomRepository.save(room);
    }

    // Get all rooms
    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    // Get a single room by id
    public Optional<Room> getRoomById(String id) {
        return roomRepository.findById(id);
    }

    // Update an existing room
    public Room updateRoom(String id, Room updatedRoom) {
        updatedRoom.setId(id);
        return roomRepository.save(updatedRoom);
    }

    // Delete a room
    public void deleteRoom(String id) {
        roomRepository.deleteById(id);
    }
}