package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsNameEntity;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class CustomField extends AbsNameEntity {

    @Column(nullable = false, name = "field_type")
    @Enumerated(EnumType.STRING)
    private CustomFieldTypeEnum fieldType;

    @OneToMany(mappedBy = "customField")
    private List<Dropdown> optionalDropdown;

    @OneToMany(mappedBy = "customField")
    private List<Label> labels;

    @OneToMany(mappedBy = "customField")
    private List<Rating> ratings;

    @OneToMany(mappedBy = "customField")
    private List<Money> moneyList;

    @Column(nullable = false, name = "table_name")
    private String tableName;


}
