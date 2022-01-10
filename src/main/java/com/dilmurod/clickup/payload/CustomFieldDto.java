package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.customField.Dropdown;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import lombok.Data;

import java.util.List;

@Data
public class CustomFieldDto {

    private CustomFieldTypeEnum fieldType;

    private String name;

    private List<DropdownDto> dropdownDto;

    private List<LabelDto> labelDto;

    private RatingDto ratingDto;

    private MoneyDto moneyDto;


}
