package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.customField.Dropdown;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class CustomFieldDto {

    private CustomFieldTypeEnum fieldType;

    private String name;

    private List<DropdownDto> dropdownDto=new ArrayList<>();

    private List<LabelDto> labelDto=new ArrayList<>();

    private RatingDto ratingDto;

    private MoneyDto moneyDto;


}
