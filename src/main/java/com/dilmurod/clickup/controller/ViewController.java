package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.entity.View;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.repository.ViewRepository;
import com.dilmurod.clickup.service.ViewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/view")
public class ViewController {

    @Autowired
    ViewRepository viewRepository;
    @Autowired
    ViewService viewService;


    @PostMapping
    public HttpEntity<?> add(@RequestBody View view){
        ApiResponse responseAdd = viewService.add(view);
        return ResponseEntity.ok(responseAdd);
    }

    @GetMapping
    public HttpEntity<?> list(){
        return ResponseEntity.ok(viewRepository.findAll());
    }

}
