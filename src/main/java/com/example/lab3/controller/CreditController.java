package com.example.lab3.controller;

import com.example.lab3.model.enums.CreditStatus;
import com.example.lab3.model.request.CreditRequest;
import com.example.lab3.model.response.CreditResponse;
import com.example.lab3.service.CreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/credits")
@RequiredArgsConstructor
public class CreditController {
    private final CreditService creditService;

    @GetMapping
    public List<CreditResponse> getCreditsByStatus(@RequestParam CreditStatus status) {
        return creditService.getCreditsByStatus(status);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void initializeCredit(@RequestBody CreditRequest request) {
        creditService.initializeCredit(request);
    }

    @PutMapping("/accept")
    @ResponseStatus(NO_CONTENT)
    public void acceptCreditByOfferID(@RequestParam Long offerId) {
        creditService.acceptCreditByOfferID(offerId);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void rejectCredit(@PathVariable Long id) {
        creditService.rejectCredit(id);
    }
}
