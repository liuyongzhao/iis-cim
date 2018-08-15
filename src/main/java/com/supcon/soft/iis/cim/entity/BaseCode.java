package com.supcon.soft.iis.cim.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity
@Table
@DynamicInsert
@DynamicUpdate
public class BaseCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private int id;
    @Column(nullable = false, length = 45)
    private String name;
    @Column(columnDefinition = "text")
    private String description;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date created;
    @Column(columnDefinition = "timestamp default current_timestamp")
    private Date updated;
    @Column
    private Date deleted;
    @Column(columnDefinition = "bit default 1")
    private boolean used;
}
