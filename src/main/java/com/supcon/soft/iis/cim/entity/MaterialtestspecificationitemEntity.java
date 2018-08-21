package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
@Data
@Entity
@Table(name="Materialtestspecificationitem")
@DynamicInsert
@DynamicUpdate
public class MaterialtestspecificationitemEntity implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column
        private Integer id;
        @Column
        private Integer specificationId;
        @Column
        private Integer propertyTagId;
        @Column
        private Integer propertyId;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(columnDefinition = "timestamp default current_timestamp")
        private Date created;
        @Column(columnDefinition = "timestamp default current_timestamp on update current_timestamp")
        private Date updated;
        @Column
        private Date deleted;
        @Column(columnDefinition = "bit")
        private Boolean used = true;
}
