package com.example.payment.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PaymentRequest {

    @NotBlank(message = "Cardholder name is required")
    private String name;

    @NotBlank(message = "Card number is required")
    private String cardNumber;

    @NotNull(message = "Amount is required")
    @Min(value = 1, message = "Minimum amount is $1.00")
    private Double amount;

    public PaymentRequest() {
    }

    public PaymentRequest(String name, String cardNumber, Double amount) {
        this.name = name;
        this.cardNumber = cardNumber;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
