package com.dilmurod.clickup.entity;

import com.dilmurod.clickup.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = true)
@Entity
public class Payment extends AbsNameEntity {

}
