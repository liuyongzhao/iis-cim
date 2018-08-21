package com.supcon.soft.iis.cim.entity;

import com.supcon.soft.iis.cim.jpa.JpaConvertSubmitterWithJson;
import com.supcon.soft.iis.cim.model.Submitter;
import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.Date;

@Data
@Entity
@Table(name="Materiallot")
@DynamicInsert
@DynamicUpdate
public class MateriallotEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column(nullable = false)
    private String code;
    @Column(columnDefinition = "text")
    private String description;
    @Column(nullable = false)
    private Integer parentId;
    @Column(nullable = false, columnDefinition = "'Planned','Existed','Disposed'")
    private Enum status;
    @Column(nullable = false)
    private DecimalFormat planQuantity;
    @Column
    private DecimalFormat initialQuantity;
    @Column
    private DecimalFormat currentQuantity;
    @Column(nullable = false)
    private Integer location;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
    private Date updated;
    @Column
    private Date deleted;
    @Column
    private Date expired;
    @Column
    private Date disposed;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter creator = new Submitter();
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter updater;
    @Convert(converter = JpaConvertSubmitterWithJson.class)
    private Submitter deleter;
}