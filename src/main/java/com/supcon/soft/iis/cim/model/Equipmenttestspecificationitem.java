package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("euipmenttestspecificationitem")
public class Equipmenttestspecificationitem implements Serializable {
    private Integer id;
    private Integer propertyId;
    private Integer propertyTagId;
    private Integer specificationId;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private List<Equipmenttestspecificationitem> items;
}