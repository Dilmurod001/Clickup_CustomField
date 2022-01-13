package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CusValRepository extends JpaRepository<CustomFieldValue,Long> {

    Optional<CustomFieldValue> findByCustomField_IdAndCustomField_TableNameOrderByValue(Long customField_id, String customField_tableName);

    List<CustomFieldValue> findAllByCustomField_Id(Long customField_id);

    List<CustomFieldValue> findAllByCustomField_IdOrderByValueAsc(Long customField_id);

    List<CustomFieldValue> findAllByCustomField_IdOrderByValueDesc(Long customField_id);
}
