package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.template.ColorEnum;
import lombok.Data;

@Data
public class LabelDto {
    private String name;
    private ColorEnum color;
}
