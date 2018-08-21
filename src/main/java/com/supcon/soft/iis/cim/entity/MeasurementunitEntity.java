package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name="Measurementunit")
@DynamicInsert
@DynamicUpdate
public class MeasurementunitEntity implements Serializable {
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
    @Column(columnDefinition = "bit")
    private Boolean used = true;
}
