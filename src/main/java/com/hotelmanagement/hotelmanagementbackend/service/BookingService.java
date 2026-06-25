package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.Booking;
import com.hotelmanagement.hotelmanagementbackend.model.Room;
import com.hotelmanagement.hotelmanagementbackend.repository.BookingRepository;
import com.hotelmanagement.hotelmanagementbackend.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Create a new booking, after checking availability
    public Booking createBooking(Booking booking) {

        boolean isAvailable = isRoomAvailable(
                booking.getRoomId(),
                booking.getCheckInDate(),
                booking.getCheckOutDate()
        );

        if (!isAvailable) {
            throw new RuntimeException("Room is not available for the selected dates");
        }

        // Calculate total amount based on number of nights
        long nights = ChronoUnit.DAYS.between(booking.getCheckInDate(), booking.getCheckOutDate());

        Optional<Room> roomOpt = roomRepository.findById(booking.getRoomId());
        if (roomOpt.isEmpty()) {
            throw new RuntimeException("Room not found");
        }

        double pricePerNight = roomOpt.get().getPricePerNight();
        booking.setTotalAmount(nights * pricePerNight);
        booking.setStatus("PENDING");

        return bookingRepository.save(booking);
    }

    // Check if a room is available for the given date range
    public boolean isRoomAvailable(String roomId, java.time.LocalDate checkIn, java.time.LocalDate checkOut) {

        List<Booking> existingBookings = bookingRepository.findByRoomId(roomId);

        for (Booking existing : existingBookings) {

            // Skip cancelled bookings - they don't block the room
            if ("CANCELLED".equals(existing.getStatus())) {
                continue;
            }

            boolean overlaps = checkIn.isBefore(existing.getCheckOutDate())
                             && checkOut.isAfter(existing.getCheckInDate());

            if (overlaps) {
                return false; // dates clash with an existing booking
            }
        }

        return true; // no clashes found
    }

    // Get all bookings
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Get a booking by id
    public Optional<Booking> getBookingById(String id) {
        return bookingRepository.findById(id);
    }

    // Get all bookings by a user
    public List<Booking> getBookingsByUser(String userId) {
        return bookingRepository.findByUserId(userId);
    }

    // Cancel a booking
    public Booking cancelBooking(String id) {
        Optional<Booking> bookingOpt = bookingRepository.findById(id);
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }
        Booking booking = bookingOpt.get();
        booking.setStatus("CANCELLED");
        return bookingRepository.save(booking);
    }
}