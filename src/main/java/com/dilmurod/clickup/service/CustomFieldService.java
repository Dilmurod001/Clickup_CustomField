package com.dilmurod.clickup.service;

import com.dilmurod.clickup.entity.customField.*;
import com.dilmurod.clickup.entity.template.CurrencyEnum;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import com.dilmurod.clickup.payload.*;
import com.dilmurod.clickup.repository.*;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class CustomFieldService {

    final
    RatingRepository ratingRepository;
    final
    CusValRepository cusValRepository;
    final
    CustomFieldRepository customFieldRepository;
    final
    DropdownRepository dropdownRepository;
    final
    LabelRepository labelRepository;
    final
    MoneyRepository moneyRepository;
    final
    TaskRepository taskRepository;
    final
    PaymentRepository paymentRepository;

    public CustomFieldService(RatingRepository ratingRepository, CusValRepository cusValRepository, CustomFieldRepository customFieldRepository, DropdownRepository dropdownRepository, LabelRepository labelRepository, MoneyRepository moneyRepository, TaskRepository taskRepository, PaymentRepository paymentRepository) {
        this.ratingRepository = ratingRepository;
        this.cusValRepository = cusValRepository;
        this.customFieldRepository = customFieldRepository;
        this.dropdownRepository = dropdownRepository;
        this.labelRepository = labelRepository;
        this.moneyRepository = moneyRepository;
        this.taskRepository = taskRepository;
        this.paymentRepository = paymentRepository;
    }

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

        if (!byId.isPresent()) return new ApiResponse("Not customField", false);
        CustomField customField = byId.get();
        customFieldValue.setCustomField(customField);


        customFieldValue.setAppropriate(custValDto.getAppropriateId());

        if (customField.getFieldType().equals(CustomFieldTypeEnum.NUMBER)) {
            try {
                Double.parseDouble(custValDto.getValue());
                customFieldValue.setValue(custValDto.getValue());

            } catch (Exception e) {
                return new ApiResponse("Oka Value ga faqat son kiritiladi !", false);
            }

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.DATE)) {
            try {

                if (custValDto.getValue().length() >= 8 && custValDto.getValue().length() >= 10) {
                    String str = custValDto.getValue().concat(" 00:00:00");

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("Asia/Tashkent"));

                    Date date = formatter.parse(str);
                    String formattedDateString = formatter.format(date);
                    customFieldValue.setValue(formattedDateString);

                } else {

                    SimpleDateFormat formatter = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
                    formatter.setTimeZone(TimeZone.getTimeZone("Asia/Tashkent"));

                    String dateInString = custValDto.getValue();
                    Date date = formatter.parse(dateInString);
                    String formattedDateString = formatter.format(date);
                    customFieldValue.setValue(formattedDateString);
                }

            } catch (Exception e) {
                return new ApiResponse("Valuega faqat => dd-M-yyyy hh:mm:ss formatda yoki dd-M-yyyy formatda kiritiladi faqat !", false);
            }


        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.DROPDOWN)) {
            Optional<Dropdown> dropdownRepositoryById = dropdownRepository.findById(Long.valueOf(custValDto.getValue()));
            if (!dropdownRepositoryById.isPresent()) {
                new ApiResponse("This dropdown id not found", false);
            }
            customFieldValue.setValue(dropdownRepositoryById.get().getId().toString());

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.LABEL)) {

            List<Long> values = new ArrayList<>();
            String value = custValDto.getValue();
            String[] words = value.split(",");

            Long[] val = new Long[words.length];

            for (int i = 0; i < words.length; i++) {
                val[i] = Long.parseLong(words[i]);
            }


            for (Long aLong : val) {
                Optional<Label> byId2 = labelRepository.findById(aLong);
                if (!byId2.isPresent()) return new ApiResponse("Not Label", false, aLong);

                values.add(byId2.get().getId());
            }
            customFieldValue.setValue(String.valueOf(values));

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.RATING)) {

            Optional<Rating> byCustomField_id = ratingRepository.findByCustomField_Id(customField.getId());
            if (!byCustomField_id.isPresent()) return new ApiResponse("Bu turdagi Rating yoq !", false);

            Rating rating = byCustomField_id.get();
            int val = Integer.parseInt(custValDto.getValue());

            if (rating.getDegree() < val) {
                return new ApiResponse("Ratingni qiymatidan katta son kiritib bolmaydi !", false);

            } else if (0 > val) return new ApiResponse("Manfiy son kiritib bolmaydi !", false);

            List<String> emoji = new ArrayList<>();
            for (int i = 0; i < val; i++) {
                emoji.add(rating.getEmoji());
            }
            customFieldValue.setValue(String.valueOf(emoji));

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.MONEY)) {

            Optional<Money> moneyRepositoryByCustomField_id = moneyRepository.findByCustomField_Id(customField.getId());
            if (!moneyRepositoryByCustomField_id.isPresent())
                return new ApiResponse("Bu turdagi Money yoq !", false);

            Money money = moneyRepositoryByCustomField_id.get();

            if (money.getCurrency().equals(CurrencyEnum.DOLLAR)) {
                double sum = Double.parseDouble(custValDto.getValue());
                customFieldValue.setValue("\uD83D\uDCB2" + (sum));

            } else if (money.getCurrency().equals(CurrencyEnum.EURO)) {
                double sum = Double.parseDouble(custValDto.getValue());
                customFieldValue.setValue("€" + (sum));

            } else if (money.getCurrency().equals(CurrencyEnum.SUM)) {
                double sum = Double.parseDouble(custValDto.getValue());
                customFieldValue.setValue("uzb" + (sum));

            } else return new ApiResponse("Bu turdagi Money yoq !", false);

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.CHECKBOX)) {

            try {
                Boolean.parseBoolean(custValDto.getValue());
            customFieldValue.setValue(String.valueOf(custValDto.getValue()));

            }catch (Exception e){
                return new ApiResponse("Valuga true yoki false qiymat kiriting !", false);
            }

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.EMAIL)) {
            String value = custValDto.getValue();
            String substring = value.substring(value.length() - 10);
            System.out.println(substring);
            if (substring.equals("@gmail.com") || substring.equals("@email.com")) {
                customFieldValue.setValue(custValDto.getValue());
            } else {
                return new ApiResponse("Emailni xato kiritdingiz", false);
            }

        } else if (customField.getFieldType().equals(CustomFieldTypeEnum.PHONE)) {
            try {
                String value = String.valueOf(custValDto.getValue());

                if (value.startsWith("+998") && value.length() == 13) {
                    value = value.substring(1);
                    Long.parseLong(value);

                    customFieldValue.setValue(custValDto.getValue());

                } else if (value.length() == 9) {
                    Long.parseLong(value);
                    customFieldValue.setValue(custValDto.getValue());

                } else
                    return new ApiResponse("Tel raqamni +998 bilan yoki operator kodi bilan 9 xonali son kiriting", false);
            } catch (Exception e) {
                return new ApiResponse("Siz tel raqamni xato kiritdiz Iltimos faqat raqam kiriting !", false);
            }
        }

        cusValRepository.save(customFieldValue);
        return new ApiResponse("saved", true);

    }

    public ApiResponse sortCustomField(Long id, String tableName, boolean isSort) {
        Optional<CustomField> byIdAndTableName = customFieldRepository.findByIdAndTableName(id, tableName);
        if (!byIdAndTableName.isPresent()) return new ApiResponse("Custom field ni value si yoq !", false);

        List<CustomFieldValue> allByCustomField_id = cusValRepository.findAllByCustomField_Id(byIdAndTableName.get().getId());

        if (isSort) {
            List<CustomFieldValue> allByCustomField_idOrderByValue = cusValRepository.findAllByCustomField_IdOrderByValueAsc(byIdAndTableName.get().getId());

            List<CustomFieldValue> allByCustomField_idOrderByValueDesc = cusValRepository.findAllByCustomField_IdOrderByValueDesc(byIdAndTableName.get().getId());

            CustomFieldValue customFieldValue = allByCustomField_id.get(0);
            CustomFieldValue customFieldValueAsc = allByCustomField_idOrderByValue.get(0);
            CustomFieldValue customFieldValueDesc = allByCustomField_idOrderByValueDesc.get(0);

            if (customFieldValue.getValue().equals(customFieldValueAsc.getValue())) {
                cusValRepository.deleteAll(allByCustomField_id);
                cusValRepository.saveAll(allByCustomField_idOrderByValueDesc);
                return new ApiResponse("Sorted Desc !", true, allByCustomField_idOrderByValueDesc);
            } else if (customFieldValue.getValue().equals(customFieldValueDesc.getValue())) {
                cusValRepository.deleteAll(allByCustomField_id);
                cusValRepository.saveAll(allByCustomField_idOrderByValue);

                return new ApiResponse("Sorted Asc !", true, allByCustomField_idOrderByValue);

            } else
                cusValRepository.deleteAll(allByCustomField_id);
            cusValRepository.saveAll(allByCustomField_idOrderByValue);
            return new ApiResponse("Sorted Asc !", true, allByCustomField_idOrderByValue);
        } else {
            return new ApiResponse("Sorted default !", true, allByCustomField_id);
        }
    }

}

