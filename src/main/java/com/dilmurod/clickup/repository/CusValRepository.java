package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CusValRepository extends JpaRepository<CustomFieldValue,Long> {
}
