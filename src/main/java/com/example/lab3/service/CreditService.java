package com.example.lab3.service;

import com.example.lab3.dao.entity.CreditEntity;
import com.example.lab3.dao.repository.CreditRepository;
import com.example.lab3.dao.repository.CustomerRepository;
import com.example.lab3.dao.repository.OfferRepository;
import com.example.lab3.dao.repository.StatusHistoryRepository;
import com.example.lab3.model.enums.CreditStatus;
import com.example.lab3.model.request.CreditRequest;
import com.example.lab3.model.response.CreditResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.lab3.mapper.CreditMapper.CREDIT_MAPPER;
import static com.example.lab3.mapper.HistoryMapper.HISTORY_MAPPER;
import static com.example.lab3.model.enums.CreditStatus.ACCEPTED;
import static com.example.lab3.model.enums.CreditStatus.DRAFT;
import static com.example.lab3.model.enums.CreditStatus.EXPIRED;
import static com.example.lab3.model.enums.CreditStatus.REJECTED;
import static java.lang.Boolean.TRUE;

@Service
@RequiredArgsConstructor
public class CreditService {
    private final CreditRepository creditRepository;
    private final CustomerRepository customerRepository;
    private final OfferRepository offerRepository;
    private final StatusHistoryRepository historyRepository;

    public List<CreditResponse> getCreditsByStatus(CreditStatus status) {
        return creditRepository.findAllByStatus(status).stream()
                .map(CREDIT_MAPPER::buildEntityToResponse)
                .collect(Collectors.toList());
    }

    public void initializeCredit(CreditRequest request) {

        var customer = customerRepository.findById(request.getCustomerId()).orElseThrow(RuntimeException::new);
        var credit = CREDIT_MAPPER.buildRequestToEntity(customer, request);
        creditRepository.save(credit);
        saveStatusHistory(credit, DRAFT);
    }

    public void acceptCreditByOfferID(Long offerId) {
        var offer = offerRepository.findById(offerId).orElseThrow(RuntimeException::new);
        var credit = offer.getCredit();
        credit.setStatus(ACCEPTED);
        offer.setAccepted(TRUE);
        credit.setAmount(offer.getAmount());
        credit.setTerm(offer.getTerm());
        credit.setInterest(offer.getInterest());
        credit.setMonthlyPayment(
                calculateMonthlyPayment(
                        offer.getAmount(),
                        offer.getTerm(),
                        offer.getInterest()
                ));
        creditRepository.save(credit);
        saveStatusHistory(credit, ACCEPTED);
    }

    public void rejectCredit(Long id) {
        var credit = creditRepository.findById(id).orElseThrow(RuntimeException::new);
        credit.setStatus(REJECTED);
        creditRepository.save(credit);
        saveStatusHistory(credit, REJECTED);
    }

    public void updateExpiredCredits() {
        var credit = creditRepository.findByCheckDateBeforeAndStatus(LocalDateTime.now(), DRAFT);
        credit.forEach(credits -> credits.setStatus(EXPIRED));
        credit.forEach((creditList -> saveStatusHistory(creditList, EXPIRED)));
        creditRepository.saveAll(credit);
    }

    private BigDecimal calculateMonthlyPayment(BigDecimal amount, Integer term, BigDecimal interest) {
        var monthlyInterestRate = interest.divide(BigDecimal.valueOf(100L));
        var monthlyPayment = amount.multiply(monthlyInterestRate).divide(BigDecimal.valueOf(term), RoundingMode.HALF_UP);
        return monthlyPayment;
    }

    private void saveStatusHistory(CreditEntity credit, CreditStatus status) {
        var history = HISTORY_MAPPER.buildEntity(credit, status);
        historyRepository.save(history);
    }
}
