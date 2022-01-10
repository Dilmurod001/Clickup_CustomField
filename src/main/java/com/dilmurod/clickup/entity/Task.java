package com.dilmurod.clickup.entity;

import com.dilmurod.clickup.constants.Constanta;
import com.dilmurod.clickup.entity.attachment.Attachment;
import com.dilmurod.clickup.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = Constanta.TABLE_TASK)

public class Task extends AbsNameEntity {
    private String description;
    private String status;
//    @OneToMany
//    private List<View> viewList;
//    @OneToOne
//    private Attachment attachment;
}
