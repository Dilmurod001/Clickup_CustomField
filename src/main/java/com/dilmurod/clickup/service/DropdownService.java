package com.dilmurod.clickup.service;

import com.dilmurod.clickup.repository.CustomFieldRepository;
import com.dilmurod.clickup.repository.DropdownRepository;
import com.dilmurod.clickup.repository.OptionalDropdownRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DropdownService {
    @Autowired
    DropdownRepository dropdownRepository;
    @Autowired
    OptionalDropdownRepository optionalDropdownRepository;
    @Autowired
    CustomFieldRepository customFieldRepository;

//    public ApiResponse addCustomField(DropdownDto dropdownDto) {
//        Dropdown dropdown = new Dropdown();
//
//        OptionDropdown optionDropdown = new OptionDropdown();
//        List<OptionDropdown> optionDropdownList = new ArrayList<>();
//
//        if (dropdownDto.getOptionalDropdownList().isEmpty())
//            return new ApiResponse("Option bo'sh bo'lmasligi kere !", false);
//
//        for (OptionDropdown optionDropdown1 : dropdownDto.getOptionalDropdownList()) {
//            optionDropdownList.addCustomField(optionDropdown1);
//            optionDropdown.setName(optionDropdown1.getName());
//        }
//
//        if (dropdownDto.getTableName().isEmpty())
//            return new ApiResponse("Table Name bo'sh bo'lmasligi kere !", false);
////
////        if (dropdownDto.getCustomFieldId() != null) {
////            Optional<CustomField> fieldRepositoryById = customFieldRepository.findById(dropdownDto.getCustomFieldId());
////            if (!fieldRepositoryById.isPresent()) return new ApiResponse("not found field", false);
////            dropdown.setCustomField(fieldRepositoryById.get());
////        }
////
////        optionalDropdownRepository.save(optionDropdown);
////
////        dropdown.setOptionDropdownList(optionDropdownList);
////        dropdown.setName(dropdownDto.getName());
////        dropdown.setTableName(dropdownDto.getTableName());
//
//        dropdownRepository.save(dropdown);
//        return new ApiResponse("Saved !", true);
//
//    }

//    public ApiResponse addCustomField(DropdownDto dropdownDto){
//        Dropdown dropdown = new Dropdown();
//
//        CustomField customField =new CustomField();
//
//        customField.setName(dropdownDto.getNameCustomField());
//        customField.setTableName(dropdownDto.getTableName());
//        customField.setField(EnumFields.valueOf(dropdownDto.getField()));
//
//        customFieldRepository.save(customField);
//
//        dropdown.setCustomField(customField);
//        dropdown.setName(dropdownDto.getNameDropDown());
//
//        dropdownRepository.save(dropdown);
//        return new ApiResponse("Saved !",true);
//    }
}

