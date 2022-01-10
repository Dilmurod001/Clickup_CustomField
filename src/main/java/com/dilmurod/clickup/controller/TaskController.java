package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.constants.Constanta;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.CustomFieldDto;
import com.dilmurod.clickup.repository.TaskRepository;
import com.dilmurod.clickup.service.CustomFieldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    CustomFieldService customFieldService;


    @PostMapping("/addCustomField")
    public HttpEntity<?> addCustomField(@RequestBody CustomFieldDto customFieldDto){
        ApiResponse responseAdd = customFieldService.addCustomField(customFieldDto, Constanta.TABLE_TASK);
        return ResponseEntity.ok(responseAdd);
    }
}
