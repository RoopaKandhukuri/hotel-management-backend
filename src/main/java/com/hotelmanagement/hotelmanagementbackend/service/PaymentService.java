package com.hotelmanagement.hotelmanagementbackend.service;

import com.hotelmanagement.hotelmanagementbackend.model.Booking;
import com.hotelmanagement.hotelmanagementbackend.model.Payment;
import com.hotelmanagement.hotelmanagementbackend.repository.BookingRepository;
import com.hotelmanagement.hotelmanagementbackend.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BookingRepository bookingRepository;

    // Make a payment for a booking
    public Payment makePayment(Payment payment) {

        Optional<Booking> bookingOpt = bookingRepository.findById(payment.getBookingId());
        if (bookingOpt.isEmpty()) {
            throw new RuntimeException("Booking not found");
        }

        Booking booking = bookingOpt.get();

        // Set payment amount from booking's total amount if not provided
        if (payment.getAmount() == null) {
            payment.setAmount(booking.getTotalAmount());
        }

        payment.setStatus("PAID");
        Payment savedPayment = paymentRepository.save(payment);

        // Confirm the booking once payment is done
        booking.setStatus("CONFIRMED");
        bookingRepository.save(booking);

        return savedPayment;
    }

    // Get payment by booking id
    public Payment getPaymentByBookingId(String bookingId) {
        return paymentRepository.findByBookingId(bookingId);
    }

    // Refund a payment
    public Payment refundPayment(String paymentId) {
        Optional<Payment> paymentOpt = paymentRepository.findById(paymentId);
        if (paymentOpt.isEmpty()) {
            throw new RuntimeException("Payment not found");
        }
        Payment payment = paymentOpt.get();
        payment.setStatus("REFUNDED");
        return paymentRepository.save(payment);
    }
}