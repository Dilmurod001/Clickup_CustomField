package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomField;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {
}
