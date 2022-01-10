package com.dilmurod.clickup.service;

import com.dilmurod.clickup.entity.customField.*;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import com.dilmurod.clickup.payload.ApiResponse;
import com.dilmurod.clickup.payload.CustomFieldDto;
import com.dilmurod.clickup.payload.DropdownDto;
import com.dilmurod.clickup.payload.LabelDto;
import com.dilmurod.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomFieldService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    CustomFieldRepository customFieldRepository;
    @Autowired
    DropdownRepository dropdownRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    MoneyRepository moneyRepository;

    public ApiResponse addCustomField(CustomFieldDto customFieldDto, String tableName) {

        CustomField customField = new CustomField();
        customField.setName(customFieldDto.getName());
        customField.setFieldType(customFieldDto.getFieldType());
        customField.setTableName(tableName);

        CustomField customField1 = customFieldRepository.save(customField);

        if (customFieldDto.getFieldType().equals(CustomFieldTypeEnum.DROPDOWN) && !customFieldDto.getDropdownDto().isEmpty()) {
            for (DropdownDto dropdownDto : customFieldDto.getDropdownDto()) {
                Dropdown dropdown = new Dropdown();
                dropdown.setName(dropdownDto.getNameDropdown());
                dropdown.setColorEnum(dropdownDto.getColor());
                dropdown.setCustomField(customField1);
                dropdownRepository.save(dropdown);
            }

        } else if (customFieldDto.getFieldType().equals(CustomFieldTypeEnum.LABEL) && !customFieldDto.getLabelDto().isEmpty()) {
            for (LabelDto labelDto : customFieldDto.getLabelDto()) {
                Label label = new Label();
                label.setName(labelDto.getName());
                label.setColor(labelDto.getColor());
                label.setCustomField(customField1);
                labelRepository.save(label);
            }

        } else if (customFieldDto.getFieldType().equals(CustomFieldTypeEnum.RATING)) {
            Rating rating = new Rating();
            rating.setEmoji(customFieldDto.getRatingDto().getEmoji());
            rating.setDegree(customFieldDto.getRatingDto().getDegree());
            rating.setCustomField(customField1);
            ratingRepository.save(rating);

        } else if (customFieldDto.getFieldType().equals(CustomFieldTypeEnum.MONEY)) {
            Money money = new Money();
            money.setCurrency(customFieldDto.getMoneyDto().getCurrency());
            money.setCustomField(customField1);
            moneyRepository.save(money);
        }

        return new ApiResponse("Saved", true);
    }

}
