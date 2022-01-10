package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsEntity;
import com.dilmurod.clickup.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

public class Rating extends AbsEntity {
    private String emoji;
    private int degree;

    @ManyToOne(fetch = FetchType.LAZY)
    CustomField customField;
}

