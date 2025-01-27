package com.example.lab3.dao.repository;

import com.example.lab3.dao.entity.StatusHistoryEntity;
import org.springframework.data.repository.CrudRepository;

public interface StatusHistoryRepository extends CrudRepository<StatusHistoryEntity, Long> {
}
