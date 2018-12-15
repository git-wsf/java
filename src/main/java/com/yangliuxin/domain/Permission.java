package com.yangliuxin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_permission")
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

	@Transient
	private List<Permission> child;

}
