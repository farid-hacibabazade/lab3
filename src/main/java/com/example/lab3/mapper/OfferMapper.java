package com.example.lab3.mapper;

import com.example.lab3.dao.entity.CreditEntity;
import com.example.lab3.dao.entity.OfferEntity;
import com.example.lab3.model.request.OfferRequest;
import com.example.lab3.model.response.OfferResponse;

import static java.lang.Boolean.FALSE;

public enum OfferMapper {
    OFFER_MAPPER;

    public OfferEntity buildToEntity(CreditEntity credit, OfferRequest request) {
        return OfferEntity.builder()
                .credit(credit)
                .amount(request.getAmount())
                .term(request.getTerm())
                .interest(request.getInterest())
                .accepted(FALSE)
                .build();
    }

    public OfferResponse buildEntityToResponse(OfferEntity entity) {
        return OfferResponse.builder()
                .id(entity.getId())
                .amount(entity.getAmount())
                .term(entity.getTerm())
                .interest(entity.getInterest())
                .accepted(entity.getAccepted())
                .createdAt(entity.getCreatedAt())
                .updatedAt(entity.getUpdatedAt())
                .build();
    }
}
