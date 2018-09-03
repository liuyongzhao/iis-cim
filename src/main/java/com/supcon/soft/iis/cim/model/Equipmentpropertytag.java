package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmentpropertytag")
public class Equipmentpropertytag implements Serializable {
        private Integer id;
        private String name;
        private String description;
        private Date created;
        private Date updated;
        private Date deleted;
        private Boolean used;
        private Submitter creator;
        private Submitter updater;
        private Submitter deleter;
        private List<Equipmentpropertytag> items;
}
