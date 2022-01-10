package com.dilmurod.clickup.entity.customField;

import com.dilmurod.clickup.entity.template.AbsEntity;
import com.dilmurod.clickup.entity.template.AbsNameEntity;
import com.dilmurod.clickup.entity.template.CurrencyEnum;
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

public class Money extends AbsEntity {
    @Enumerated(EnumType.STRING)
    private CurrencyEnum currency;

    @ManyToOne(fetch = FetchType.LAZY)
    CustomField customField;
}
