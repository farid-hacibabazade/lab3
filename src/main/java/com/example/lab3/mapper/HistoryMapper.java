package com.example.lab3.mapper;

import com.example.lab3.dao.entity.CreditEntity;
import com.example.lab3.dao.entity.StatusHistoryEntity;
import com.example.lab3.model.enums.CreditStatus;

import java.time.LocalDateTime;

public enum HistoryMapper {
    HISTORY_MAPPER;

    public StatusHistoryEntity buildEntity(CreditEntity credit, CreditStatus status) {
        return StatusHistoryEntity.builder()
                .credit(credit)
                .status(status)
                .createdAt(LocalDateTime.now())
                .build();
    }
}
