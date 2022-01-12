package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.customField.CustomField;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CustValDto {
    private Long id;
    private String value;
    private long appropriateId;
//    private List<Long> valueList;
}
