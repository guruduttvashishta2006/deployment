package com.example.payment.controller;

import com.example.payment.model.PaymentRequest;
import com.example.payment.service.PaymentService;
import com.example.payment.service.PaymentService.PaymentResult;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("paymentRequest", new PaymentRequest());
        return "payment";
    }

    @PostMapping("/checkout")
    public String processPayment(@Valid @ModelAttribute("paymentRequest") PaymentRequest request,
                                 BindingResult bindingResult,
                                 Model model) {
        if (bindingResult.hasErrors()) {
            return "payment";
        }

        PaymentResult result = paymentService.processPayment(request);
        model.addAttribute("success", result.isSuccess());
        model.addAttribute("message", result.getMessage());
        model.addAttribute("transactionId", result.getTransactionId());
        model.addAttribute("name", request.getName());
        model.addAttribute("amount", request.getAmount());

        return "result";
    }
}
