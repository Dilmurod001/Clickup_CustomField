package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsNameEntity;
import com.dilmurod.clickup.entity.template.CustomFieldTypeEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @JsonIgnore
    @OneToMany(mappedBy = "customField")
    private List<Dropdown> optionalDropdown;

    @JsonIgnore
    @OneToMany(mappedBy = "customField")
    private List<Label> labels;

    @Column(nullable = false, name = "table_name")
    private String tableName;


}
