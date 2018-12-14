package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_permission")
@Data
public class Permission extends BaseEntity<Long> {

	private static final long serialVersionUID = 6180869216498363919L;

	@Column(name = "parentId")
	private Long parentId;

	@Column(name = "name")
	private String name;

	@Column(name = "css")
	private String css;

	@Column(name = "href")
	private String href;

	@Column(name = "type")
	private Integer type;

	@Column(name = "permission")
	private String permission;

	@Column(name = "sort")
	private Integer sort;

}
