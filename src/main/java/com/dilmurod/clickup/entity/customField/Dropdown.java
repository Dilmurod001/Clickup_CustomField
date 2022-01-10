package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsNameEntity;
import com.dilmurod.clickup.entity.template.ColorEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Dropdown extends AbsNameEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private CustomField customField;

    @Enumerated(EnumType.STRING)
    private ColorEnum colorEnum;
}
