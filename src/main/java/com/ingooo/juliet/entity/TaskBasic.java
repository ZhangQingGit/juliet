package com.ingooo.juliet.entity;

import lombok.Data;

import java.util.Date;

@Data
public class TaskBasic {
    private Integer rid;
    private String cname;
    private String cemail;
    private Date tdate;
    private String pname;
    private String pmoney;
    private String findate;
    private String tasktype;
}
