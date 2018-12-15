package com.yangliuxin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "file_info")
@Data
public class FileInfo extends BaseEntity<String> {

	private static final long serialVersionUID = -5761547882766615438L;

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
