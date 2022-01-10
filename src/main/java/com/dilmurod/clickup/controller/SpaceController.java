package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.TaskDto;
import com.dilmurod.clickup.repository.TaskRepository;
import com.dilmurod.clickup.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/space")
public class SpaceController {

    @Autowired
    TaskRepository spaceRepository;
    @Autowired
    TaskService spaceService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody TaskDto spaceDto) {
        ApiResponse responseAdd = spaceService.add(spaceDto);
        return ResponseEntity.status(responseAdd.isSuccess() ? 201 : 409).body(responseAdd);
    }

    @GetMapping
    public HttpEntity<?> list(){
        return ResponseEntity.ok(spaceRepository.findAll());
    }
}
