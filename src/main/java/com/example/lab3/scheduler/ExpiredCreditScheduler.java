package com.example.lab3.scheduler;

import com.example.lab3.service.CreditService;
import lombok.RequiredArgsConstructor;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ExpiredCreditScheduler {
    private final CreditService creditService;

    @Scheduled(fixedDelayString = "PT24H")
    @SchedulerLock(name = "updateExpiredCredits", lockAtLeastFor = "PT1S", lockAtMostFor = "PT1M")
    public void updateExpiredCredits(){
        creditService.updateExpiredCredits();
    }
}
