package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomField;
import com.dilmurod.clickup.entity.customField.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingRepository extends JpaRepository<Rating, Long> {
}
