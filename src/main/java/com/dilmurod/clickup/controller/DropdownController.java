package com.dilmurod.clickup.controller;

import com.dilmurod.clickup.entity.customField.Dropdown;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.DropdownDto;
import com.dilmurod.clickup.repository.DropdownRepository;
import com.dilmurod.clickup.service.DropdownService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dropdown")
public class DropdownController {

    @Autowired
    DropdownRepository dropdownRepository;
    @Autowired
    DropdownService dropdownService;


//    @PostMapping
//    public HttpEntity<?> addCustomField(@RequestBody DropdownDto dropdownDto){
//        ApiResponse addCustomField = dropdownService.addCustomField(dropdownDto);
//        return ResponseEntity.status(addCustomField.isSuccess() ? 201 : 409).body(addCustomField.getMessage());
//    }
}
