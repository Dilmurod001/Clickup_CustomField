package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.Money;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MoneyRepository extends JpaRepository<Money, Long> {
}
