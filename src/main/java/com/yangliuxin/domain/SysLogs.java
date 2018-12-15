package com.yangliuxin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_logs")
@Data
public class SysLogs extends BaseEntity<Long> {

	private static final long serialVersionUID = -7809315432127036583L;

	@Column(name = "user")
	private SysUser user;

	@Column(name = "module")
	private String module;

	@Column(name = "flag")
	private Boolean flag;

	@Column(name = "remark")
	private String remark;


}
