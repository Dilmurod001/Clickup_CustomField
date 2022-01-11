package com.dilmurod.clickup.entity;

import com.dilmurod.clickup.entity.template.AbsEntity;
import com.dilmurod.clickup.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Timestamp;

@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Payment extends AbsEntity {

    @Column(name = "type")
    private String type;

    @Column(nullable = false)
    private  Double paySum;

    private Timestamp payDate = new Timestamp(System.currentTimeMillis());
}
