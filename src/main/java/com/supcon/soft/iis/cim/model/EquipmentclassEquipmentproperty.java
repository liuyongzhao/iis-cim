package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@RedisHash("equipmentclassEquipmentproperty")
public class EquipmentclassEquipmentproperty implements Serializable {
    private Integer id;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private Integer equipment_class_id;
    private Integer equipment_property_id;
    private Integer equipment_property_tag_id;
    private String validtime;
    private List<EquipmentclassEquipmentproperty> items;
}

