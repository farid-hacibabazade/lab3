package com.example.lab3.model.response;

import com.example.lab3.model.enums.CreditStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.Enumerated;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static javax.persistence.EnumType.STRING;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreditResponse {
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal interest;
    private BigDecimal monthlyPayment;
    private BigDecimal requestedAmount;
    private LocalDateTime checkDate;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
