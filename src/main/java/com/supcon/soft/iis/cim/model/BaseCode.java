package com.supcon.soft.iis.cim.model;

import lombok.Data;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * BaseCode [Data Transport Object]
 * @author qiyuqi
 */
@Data
@RedisHash("baseCode")
public class BaseCode implements Serializable {
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
    private List<BaseCodeItem> items;
}
