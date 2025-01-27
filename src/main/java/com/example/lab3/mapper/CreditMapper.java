package com.example.lab3.mapper;

import com.example.lab3.dao.entity.CreditEntity;
import com.example.lab3.dao.entity.CustomerEntity;
import com.example.lab3.model.request.CreditRequest;
import com.example.lab3.model.response.CreditResponse;

import java.time.LocalDateTime;

import static com.example.lab3.model.enums.CreditStatus.DRAFT;

public enum CreditMapper {
    CREDIT_MAPPER;

    public CreditResponse buildEntityToResponse(CreditEntity entity) {
        return CreditResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .term(entity.getTerm())
                .interest(entity.getInterest())
                .monthlyPayment(entity.getMonthlyPayment())
                .requestedAmount(entity.getRequestedAmount())
                .checkDate(entity.getCheckDate())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }

    public CreditEntity buildRequestToEntity(CustomerEntity customer, CreditRequest request) {
        return CreditEntity.builder()
                .customer(customer)
                .requestedAmount(request.getRequestedAmount())
                .status(DRAFT)
                .checkDate(LocalDateTime.now().plusDays(2))
                .build();
    }
}
