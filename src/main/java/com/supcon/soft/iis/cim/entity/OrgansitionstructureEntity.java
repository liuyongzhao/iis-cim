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
@Table(name="Organsitionstructure")
@DynamicInsert
@DynamicUpdate
public class OrgansitionstructureEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 45)
    private String code;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(nullable = false, length = 45)
    private String description;
    @Column(length = 45)
    private Integer orgType;
    @Column(length = 45)
    private String parentId;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updated;
    @Column(columnDefinition = "timestamp")
    private Date deleted;
    @Column
    private Boolean used = true;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter creator = new Submitter();
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter updater;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter deleter;
}