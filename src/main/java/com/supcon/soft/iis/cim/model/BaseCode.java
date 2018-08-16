package com.supcon.soft.iis.cim.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
class BaseCode {
    private int id;
    private String name;
    private String description;
    private Date created;
    private Date updated;
    private Date deleted;
    private boolean used;
    private Submitter creator;
    private Submitter updater;
    private Submitter deleter;
    private List<BaseCodeItem> items;
}
