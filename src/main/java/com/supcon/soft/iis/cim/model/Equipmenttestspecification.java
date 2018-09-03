package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmenttestspecification")
public class Equipmenttestspecification implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private Integer equipmentClassId;
    private Date created;
    private Date updated;
    private Date deleted;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private Boolean used;
    private List<Equipmenttestspecification> items;
}