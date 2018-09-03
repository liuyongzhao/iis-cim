package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmentproperty")
public class Equipmentproperty implements Serializable {
    private Integer id;
    private Integer base_code_id;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private String default_value;
    private String description;
    private Integer dimension_id;
    private String name;
    private Integer parent_id;
    private Integer unit_id;
    private String validtime;
    private String valuetype;
    private List<Equipmentproperty> items;
}