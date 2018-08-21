package com.supcon.soft.iis.cim.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;

@Data
@Entity
@Table(name="Operationmaterialbillitem")
@DynamicInsert
@DynamicUpdate
public class OperationmaterialbillitemEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private Integer operationMaterialBillId;
    @Column(nullable = false)
    private Integer materialClassId;
    @Column(nullable = false, length = 45)
    private String useType;
    @Column
    private DecimalFormat quantity;
    @Column
    private Integer dimensionId;
    @Column
    private Integer unitId;


}
