package com.example.lab3.dao.repository;

import com.example.lab3.dao.entity.OfferEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OfferRepository extends CrudRepository<OfferEntity, Long> {

    List<OfferEntity> findByCredit_Id(Long creditId);

    @Override
    @EntityGraph(attributePaths = {"credit"})
    Optional<OfferEntity> findById(Long id);
}
