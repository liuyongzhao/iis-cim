package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmentpropertyvaluerestrion")
public class Equipmentpropertyvaluerestrion implements Serializable {
    private Integer id;
    private String value;
    private String criteria;
    private Integer equipmentId;
    private Integer equipmentPropertyId;
    private Date created;
    private Date updated;
    private Date deleted;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private List<Equipmentpropertyvaluerestrion> items;
}