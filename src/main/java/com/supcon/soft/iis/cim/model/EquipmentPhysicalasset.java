package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmentPhysicalasset")
public class EquipmentPhysicalasset implements Serializable {
    private Integer id;
    private Integer equipment_id;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private Integer physical_asset_id;
    private Date expired;
    private List<EquipmentPhysicalasset> items;
}

