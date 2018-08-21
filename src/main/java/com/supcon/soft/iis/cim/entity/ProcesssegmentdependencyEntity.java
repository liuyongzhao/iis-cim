package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
@Data
@Entity
@Table(name="Processsegmentdependency")
@DynamicInsert
@DynamicUpdate
public class ProcesssegmentdependencyEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
    private Integer processSegmentId;
    @Column
    private Integer subjectId;
    @Column(length = 45)
    private String objectId;
    @Column(length = 45)
    private String dependency;
    @Column(length = 45)
    private String duration;
}