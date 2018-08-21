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
@Table(name="Operationdefinition")
@DynamicInsert
@DynamicUpdate
public class OperationdefinitionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false, length = 45)
    private String code;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private Integer processSegmentId;
    @Column(nullable = false, length = 45)
    private String operationType;
    @Column(length = 45)
    private String ref;
    @Column(nullable = false)
    private Date publishTime;
    @Column
    private Date expiredTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updated;
    @Column
    private Date deleted;
    @Column(columnDefinition = "bit")
    private Boolean used = true;
    @Column(nullable = false)
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter creator = new Submitter();
    @Column(nullable = false)
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter updater;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter deleter;
}
