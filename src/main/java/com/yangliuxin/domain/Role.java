package com.yangliuxin.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role")
@Data
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -3802292814767103648L;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

}
