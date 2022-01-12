package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.Money;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    Optional<Money> findByCustomField_Id(Long customField_id);
}
