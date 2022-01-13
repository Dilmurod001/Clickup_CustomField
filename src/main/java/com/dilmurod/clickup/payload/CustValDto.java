package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.customField.CustomField;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Data
public class CustValDto {

    @NotNull
    private Long id;
    private String value;
    @NotNull
    private Long appropriateId;
//    private List<Long> valueList;
}
