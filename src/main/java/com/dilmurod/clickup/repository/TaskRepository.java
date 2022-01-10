package com.dilmurod.clickup.repository;

import com.dilmurod.clickup.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
