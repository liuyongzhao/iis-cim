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
@Table(name="Processsegmentmaterialspecification")
@DynamicInsert
@DynamicUpdate
public class ProcesssegmentmaterialspecificationEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Integer id;
        @Column(nullable = false)
        private Integer processSegmentId;
        @Column(nullable = false)
        private Integer materialClassId;
        @Column(nullable = false, length = 45)
        private String useType;
        @Column(nullable = false)
        private DecimalFormat quantity;
        @Column(nullable = false)
        private Integer unitId;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(columnDefinition = "timestamp default current_timestamp")
        private Date created;
        @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
        private Date updated;
        @Column
        private Date deleted;
        @Column(columnDefinition = "bit")
        private Boolean used = true;
        @Convert(converter = JpaConvertSubmitterWithJson.class)
        private Submitter creator = new Submitter();
        @Convert(converter = JpaConvertSubmitterWithJson.class)
        private Submitter updater;
        @Convert(converter = JpaConvertSubmitterWithJson.class)
        private Submitter deleter;
}
