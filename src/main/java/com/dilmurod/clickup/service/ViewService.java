package com.dilmurod.clickup.service;

import com.dilmurod.clickup.entity.View;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.repository.ViewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {
    @Autowired
    ViewRepository viewRepository;


    public ApiResponse add(View view) {
        View view1 = new View();
        view1.setName(view.getName());
        view1.setActive(view.isActive());

        viewRepository.save(view1);
        return new ApiResponse("Saved !", true);
    }
}
