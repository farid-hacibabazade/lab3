package com.example.lab3.controller;

import com.example.lab3.model.request.OfferRequest;
import com.example.lab3.model.response.OfferResponse;
import com.example.lab3.service.OfferService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("v1/offers")
@RequiredArgsConstructor
public class OfferController {
    private final OfferService offerService;

    @PostMapping
    @ResponseStatus(CREATED)
    public void addOffer(@RequestBody OfferRequest request) {
        offerService.addOffer(request);
    }

    @GetMapping
    public List<OfferResponse> getOffersByCreditId(@RequestParam Long creditId) {
        return offerService.getOffersByCreditId(creditId);
    }

}