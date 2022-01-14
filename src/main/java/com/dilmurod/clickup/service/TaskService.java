package com.dilmurod.clickup.service;

import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.repository.AttachmentRepository;
import com.dilmurod.clickup.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
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
