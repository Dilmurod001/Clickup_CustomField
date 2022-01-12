package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomField;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomFieldRepository extends JpaRepository<CustomField, Long> {

//    List<CustomField> findAllByNameAndTableName(String name, String tableName);


    Optional<CustomField> findAllByNameAndTableName(String name, String tableName);


    List<CustomField> findAllByTableNameAndFieldType(String tableName, CustomFieldTypeEnum fieldType);
}
