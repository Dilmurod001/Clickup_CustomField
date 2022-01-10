package com.dilmurod.clickup.payload;

import lombok.Data;

import java.util.List;

@Data
public class TaskDto {
    private String name;
    private String description;
    private String status;
//    private List<Long> viewList;
}
