package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.entity.customField.CustomField;
import com.dilmurod.clickup.entity.customField.CustomFieldValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query(value = "select * from custom_field where table_name = 'task'", nativeQuery = true)
    List<?> getAllByCustomField();

    @Query(value = "select c.value,cf.name, c.custom_field_id, c.appropriate from custom_field_value c join custom_field cf on cf.id = c.custom_field_id where cf.table_name = 'task'", nativeQuery = true)
            List<?> getAll();
}
