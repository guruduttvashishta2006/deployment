package com.example.payment.controller;

import com.example.payment.model.PaymentRequest;
import com.example.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        model.addAttribute("paymentRequest", new PaymentRequest());
        return "payment";
    }

    @PostMapping("/payment")
    public String processPayment(@Valid PaymentRequest paymentRequest,
                                  BindingResult bindingResult,
                                  Model model) {
        if (bindingResult.hasErrors()) {
            return "payment";
        }

        boolean success = paymentService.processPayment(paymentRequest);
        model.addAttribute("name", paymentRequest.getName());
        model.addAttribute("amount", paymentRequest.getAmount());

        if (success) {
            String txnId = "TXN-" + System.currentTimeMillis();
            model.addAttribute("txnId", txnId);
            return "success";
        } else {
            return "failure";
        }
    }
}
