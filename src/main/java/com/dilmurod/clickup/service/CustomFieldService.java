package com.dilmurod.clickup.service;

import com.dilmurod.clickup.constants.Constanta;
import com.dilmurod.clickup.entity.Payment;
import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.entity.customField.*;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import com.dilmurod.clickup.payload.*;
import com.dilmurod.clickup.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomFieldService {

    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    CusValRepository cusValRepository;
    @Autowired
    CustomFieldRepository customFieldRepository;
    @Autowired
    DropdownRepository dropdownRepository;
    @Autowired
    LabelRepository labelRepository;
    @Autowired
    MoneyRepository moneyRepository;
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    PaymentRepository paymentRepository;

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

    public ApiResponse list(Long id, String tableName) {

        Optional<CustomField> byId = customFieldRepository.findById(id);
        if (!byId.isPresent()) return new ApiResponse("Bu nomdagi customField yoq !", false);
        CustomField customField = byId.get();

        CustomFieldTypeEnum fieldType = customField.getFieldType();
        if (fieldType.equals(CustomFieldTypeEnum.DROPDOWN)) {

            Optional<CustomField> dropdown = customFieldRepository.findAllByNameAndTableName(customField.getName(), tableName);

            //        List<Dropdown> allByOrderByNameAscCustomField = dropdownRepository.findAllByCustomField(dropdown.get());


//        return new ApiResponse("Mana list => ", true, allByOrderByNameAscCustomField);
            return new ApiResponse("Mana list => ", true, dropdown.get().getOptionalDropdown());

        } else if (fieldType.equals(CustomFieldTypeEnum.LABEL)) {
            Optional<CustomField> label = customFieldRepository.findAllByNameAndTableName(customField.getName(), tableName);
            return new ApiResponse("Mana list => ", true, label.get().getLabels());

        }
        return new ApiResponse("Mana => ", true, customField);

    }

    public ApiResponse addCustVal(CustValDto custValDto) throws ParseException {
        CustomFieldValue customFieldValue = new CustomFieldValue();
        Optional<CustomField> byId = customFieldRepository.findById(custValDto.getId());
        CustomField customField = byId.get();

        if (!byId.isPresent()) return new ApiResponse("Not customField", false);
        customFieldValue.setCustomField(customField);


        if (customField.getTableName().equals(Constanta.TABLE_TASK)) {
            Optional<Task> byId1 = taskRepository.findById(custValDto.getAppropriateId());
            customFieldValue.setAppropriate(byId1.get().getId());

            if (customField.getFieldType().equals(CustomFieldTypeEnum.NUMBER)) {
                customFieldValue.setValue(String.valueOf(custValDto.getValue()));

            } else if (customField.getFieldType().equals(CustomFieldTypeEnum.DATE)) {
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(custValDto.getValue());
                customFieldValue.setValue(String.valueOf(date1));

            }else if(customField.getFieldType().equals(CustomFieldTypeEnum.LABEL)){
                List<Long> values = new ArrayList<>();
                String value = custValDto.getValue();
                String[] words=value.split(",");

                Long[] val =new Long[words.length];

                for (int i = 0; i < words.length; i++) {
                    val[i] = Long.parseLong(words[i]);
                }


//                System.out.println(Arrays.toString(words));

                for (Long aLong : val) {
                    System.out.println(aLong);
                    Optional<Label> byId2 = labelRepository.findById(aLong);
                    if (!byId2.isPresent()) return new ApiResponse("Not Label", false,aLong);

                    values.add(byId2.get().getId());
                }
                customFieldValue.setValue(String.valueOf(values));
            }

        } else if (customField.getTableName().equals(Constanta.TABLE_PAYMENT)) {
            Optional<Payment> byId1 = paymentRepository.findById(custValDto.getAppropriateId());
            customFieldValue.setAppropriate(byId1.get().getId());
        }

        cusValRepository.save(customFieldValue);
        return new ApiResponse("saved", true);


    }
}
