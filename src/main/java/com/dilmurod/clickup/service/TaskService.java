package com.dilmurod.clickup.service;

import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.entity.View;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.TaskDto;
import com.dilmurod.clickup.repository.AttachmentRepository;
import com.dilmurod.clickup.repository.TaskRepository;
import com.dilmurod.clickup.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ViewRepository viewRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public ApiResponse addTask(Task task) {

        Task task1 = new Task();
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setStatus(task.getStatus());

        taskRepository.save(task1);
        return new ApiResponse("Saved !", true);
    }
}
