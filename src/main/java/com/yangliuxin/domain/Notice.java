package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "tb_notice")
@Data
public class Notice extends BaseEntity<Long> {

	private static final long serialVersionUID = -4401913568806243090L;

	@Column(name = "title")
	private String title;

	@Column(name = "content")
	private String content;

	@Column(name = "status")
	private Integer status;


}
