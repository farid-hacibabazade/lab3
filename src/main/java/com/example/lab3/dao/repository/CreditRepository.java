package com.example.lab3.dao.repository;

import com.example.lab3.dao.entity.CreditEntity;
import com.example.lab3.model.enums.CreditStatus;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CreditRepository extends CrudRepository<CreditEntity, Long> {

    List<CreditEntity> findAllByStatus(CreditStatus status);

    List<CreditEntity> findByCheckDateBeforeAndStatus(LocalDateTime checkDate, CreditStatus status);
}
