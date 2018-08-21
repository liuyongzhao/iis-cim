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
@Table(name="MateriallotMaterialdefinition")
@DynamicInsert
@DynamicUpdate
public class MateriallotMaterialdefinitionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private Integer materialLotId;
    @Column(nullable = false, length = 45)
    private String materialDefinitionId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column
    private Date terminated;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter creator = new Submitter();
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter terminator;
}