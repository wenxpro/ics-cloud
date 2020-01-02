package com.ics.cloud.common.system.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Setter
@Getter
public class SysOrgTreeBean {

    private String id;

    private String name;

    private String parent_id;

    private String parent_name;

    private String description;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
    private Date create_date;

    private List<SysOrgTreeBean> children;

}
