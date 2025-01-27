package com.example.lab3.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferResponse {
    private Long id;
    private BigDecimal amount;
    private Integer term;
    private BigDecimal interest;
    private Boolean accepted;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
