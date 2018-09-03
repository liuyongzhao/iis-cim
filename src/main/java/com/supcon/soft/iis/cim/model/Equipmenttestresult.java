package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Data
@RedisHash("equipmenttestresult")
public class Equipmenttestresult implements Serializable {
    private Integer id;
    private Integer specificationId;
    private Date testdate;
    private String description;
    private Date created;
    private Date updated;
    private Date deleted;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private List<Equipmenttestresult> items;
}