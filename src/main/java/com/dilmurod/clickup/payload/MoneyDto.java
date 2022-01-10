package com.dilmurod.clickup.payload;

import com.dilmurod.clickup.entity.template.CurrencyEnum;
import lombok.Data;

@Data
public class MoneyDto {
    private CurrencyEnum currency;
}
