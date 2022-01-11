package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.Payment;
import com.dilmurod.clickup.entity.Task;
import com.dilmurod.clickup.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomFieldValue extends AbsEntity {
    @ManyToOne
    private CustomField customField;

    private String value;

    private Long appropriate;


}
