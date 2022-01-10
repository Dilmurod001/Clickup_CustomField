package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.customField.CustomField;
import com.dilmurod.clickup.entity.customField.Dropdown;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DropdownRepository extends JpaRepository<Dropdown, Long> {

    List<Dropdown> findAllByCustomField_TableNameAndCustomField_Name(String customField_tableName, String customField_name);
}
