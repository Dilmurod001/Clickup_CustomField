package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomField;
import com.dilmurod.clickup.entity.customField.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RatingRepository extends JpaRepository<Rating, Long> {

    Optional<Rating> findByCustomField_Id(Long customField_id);
}
