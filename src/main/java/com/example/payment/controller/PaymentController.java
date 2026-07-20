package com.example.payment.controller;

import com.example.payment.model.PaymentRequest;
import com.example.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/api/payment")
    public boolean processPayment(@Valid @RequestBody PaymentRequest paymentRequest) {
        return paymentService.processPayment(paymentRequest);
    }
}
