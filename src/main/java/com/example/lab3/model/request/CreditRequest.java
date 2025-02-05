package com.example.lab3.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreditRequest {
    private Long customerId;
    private BigDecimal requestedAmount;
}
