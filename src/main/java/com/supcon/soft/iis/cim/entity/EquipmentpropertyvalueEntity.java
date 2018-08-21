package com.supcon.soft.iis.cim.entity;

import com.supcon.soft.iis.cim.jpa.JpaConvertSubmitterWithJson;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table(name="Equipmentpropertyvalue")
@DynamicInsert
@DynamicUpdate
public class EquipmentpropertyvalueEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private Integer equipmentId;
    @Column
    private Integer equipmentPropertyTagId;
    @Column(nullable = false)
    private Integer equipmentPropertyId;
    @Column(nullable = false, length = 45)
    private String value;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false)
    private Date timestamp;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updated;
    @Column
    private Date deleted;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter creator = new Submitter();
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter updater;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter deleter;
}