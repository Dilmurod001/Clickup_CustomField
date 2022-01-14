package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LabelRepository extends JpaRepository<Label,Long> {
    @Query(value = "SELECT COUNT(*) FROM label WHERE id IN (:ids)",nativeQuery = true)
    Integer existsAllById(Iterable<Long>ids);

}
