package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.template.ColorEnum;
import lombok.Data;

@Data
public class DropdownDto {
    private String nameDropdown;
    private ColorEnum color;

//    private List<OptionDropdown> optionalDropdownList;
//    private String nameCustomField;
//    private String field;
//    private String tableName;
//    private CustomField customField;

}
