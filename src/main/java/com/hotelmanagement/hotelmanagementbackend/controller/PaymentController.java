package com.hotelmanagement.hotelmanagementbackend.controller;

import com.hotelmanagement.hotelmanagementbackend.model.Payment;
import com.hotelmanagement.hotelmanagementbackend.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    // POST: Make a payment
    @PostMapping
    public ResponseEntity<?> makePayment(@RequestBody Payment payment) {
        try {
            Payment savedPayment = paymentService.makePayment(payment);
            return ResponseEntity.ok(savedPayment);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // GET: Get payment by booking id
    @GetMapping("/booking/{bookingId}")
    public ResponseEntity<Payment> getPaymentByBookingId(@PathVariable String bookingId) {
        Payment payment = paymentService.getPaymentByBookingId(bookingId);
        if (payment == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(payment);
    }

    // PUT: Refund a payment
    @PutMapping("/{id}/refund")
    public ResponseEntity<?> refundPayment(@PathVariable String id) {
        try {
            Payment refunded = paymentService.refundPayment(id);
            return ResponseEntity.ok(refunded);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
