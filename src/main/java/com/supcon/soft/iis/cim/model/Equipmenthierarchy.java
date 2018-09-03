package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmenthierarchy")
public class Equipmenthierarchy implements Serializable {
    private Integer id;
    private String code;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private String equipment_type;
    private String name;
    private String description;
    private List<Equipmenthierarchy> items;
}


