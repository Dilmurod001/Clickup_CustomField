package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsNameEntity;
import com.dilmurod.clickup.entity.template.ColorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Label extends AbsNameEntity {
    @Enumerated(EnumType.STRING)
    private ColorEnum color;

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomField customField;
}
