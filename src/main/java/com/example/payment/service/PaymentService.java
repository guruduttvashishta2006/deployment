package com.example.payment.service;

import com.example.payment.model.PaymentRequest;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    /**
     * Simulates payment processing.
     * Cards starting with '4' are treated as successful (Visa-like).
     */
    public boolean processPayment(PaymentRequest request) {
        if (request == null || request.getCardNumber() == null) {
            return false;
        }
        return request.getCardNumber().trim().startsWith("4");
    }
}
