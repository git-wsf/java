package com.yangliuxin.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_dict")
@Data
public class Dict extends BaseEntity<Long> {

	private static final long serialVersionUID = -2431140186410912787L;
	@Column(name="type")
	private String type;

	@Column(name="k")
	private String k;

	@Column(name="val")
	private String val;

}
