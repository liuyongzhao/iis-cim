package com.supcon.soft.iis.cim.model;

import lombok.Data;

@Data
public class Submitter {
    private String userId = "0";
    private String userName = "System";
    private String personId;
    private String personName;
    private String departmentId;
    private String departmentName;
    private String positionId;
    private String positionName;
}
