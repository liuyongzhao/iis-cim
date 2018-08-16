package com.supcon.soft.iis.cim.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Data
public class BaseCodeItem implements Serializable {
    private Integer id;
    private String code;
    private String value;
    private Integer baseCodeId;
    private List<BaseCodeItem> items;
    private Integer parentId;
    private Date created;
    private Date updated;
    private Date deleted;
    private Boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
}
