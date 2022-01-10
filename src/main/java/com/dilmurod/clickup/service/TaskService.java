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
    TaskRepository spaceRepository;
    @Autowired
    ViewRepository viewRepository;
    @Autowired
    AttachmentRepository attachmentRepository;


    public ApiResponse add(TaskDto spaceDto) {

//        List<View> viewList = new ArrayList<>();
//        for (Long l : spaceDto.getViewList()) {
//            Optional<View> byId = viewRepository.findById(l);
//            if (!byId.isPresent()) return new ApiResponse("Not view", false);
//
//            viewList.addCustomField(byId.get());
//        }

        Task space = new Task();

//        if (spaceDto.getPhotoId() > 0) {
//            Optional<Attachment> optionalAttachment = attachmentRepository.findById(spaceDto.getPhotoId());
//            if (!optionalAttachment.isPresent()) return new ApiResponse("Not found Photo", false);
//            space.setAttachment(optionalAttachment.get());
//
//        }

        space.setName(spaceDto.getName());
        space.setDescription(spaceDto.getDescription());
//        space.setViewList(viewList);

        spaceRepository.save(space);
        return new ApiResponse("Saved !", true);
    }
}
