package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="MaterialclassMateriadefinition")
@DynamicInsert
@DynamicUpdate
public class MaterialclassMateriadefinitionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer materialClassID;
    @Column(nullable = false)
    private Integer materialDefinitionID;

}