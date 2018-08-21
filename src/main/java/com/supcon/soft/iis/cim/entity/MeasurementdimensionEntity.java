package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name="Measurementdimension")
@DynamicInsert
@DynamicUpdate
public class MeasurementdimensionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Column(length = 45)
    private String symbol;
    @Column(columnDefinition = "text")
    private String expression;
    @Column(nullable = false)
    private Integer lengthFactor;
    @Column(nullable = false)
    private Integer massFactor;
    @Column(nullable = false)
    private Integer timeFactor;
    @Column(nullable = false)
    private Integer electricCurrencyFactor;
    @Column(nullable = false)
    private Integer temperatureFactor;
    @Column(nullable = false)
    private Integer amountOfSubstanceFactor;
    @Column(nullable = false)
    private Integer lightIntensityFactor;
    @Column(columnDefinition = "bit")
    private Boolean used = true;
}
