package com.supcon.soft.iis.cim.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@SuppressWarnings("WeakerAccess")
@Data
public class BaseCodeItem {
    private int id;
    private String code;
    private String value;
    private int baseCodeId;
    private List<BaseCodeItem> items;
    private int parentId;
    private Date created;
    private Date updated;
    private Date deleted;
    private boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
}
