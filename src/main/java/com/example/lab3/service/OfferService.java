package com.example.lab3.service;

import com.example.lab3.dao.entity.OfferEntity;
import com.example.lab3.dao.repository.CreditRepository;
import com.example.lab3.dao.repository.OfferRepository;
import com.example.lab3.mapper.OfferMapper;
import com.example.lab3.model.request.OfferRequest;
import com.example.lab3.model.response.OfferResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.lab3.mapper.OfferMapper.OFFER_MAPPER;

@Service
@RequiredArgsConstructor
public class OfferService {
    private final OfferRepository offerRepository;
    private final CreditRepository creditRepository;

    public void addOffer(OfferRequest request) {
        var credit = creditRepository.findById(request.getCreditId()).orElseThrow(RuntimeException::new);
        var offer = OFFER_MAPPER.buildToEntity(credit, request);
        offerRepository.save(offer);
    }

    public List<OfferResponse> getOffersByCreditId(Long creditId){
        var offers = offerRepository.findByCredit_Id(creditId);
        return offers.stream()
                .map(OFFER_MAPPER::buildEntityToResponse)
                .collect(Collectors.toList());
    }
}
