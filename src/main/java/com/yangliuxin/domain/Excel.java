package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_excel")
@Data
public class Excel {

    private static final long serialVersionUID = -5761547882766615438L;

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "createTime")
    private Date createTime = new Date();

    @Column(name = "updateTime")
    private Date updateTime = new Date();

    @Column(name = "contentType")
    private String contentType;

    @Column(name = "size")
    private long size;

    @Column(name = "path")
    private String path;

    @Column(name = "url")
    private String url;

    @Column(name = "type")
    private Integer type;


}