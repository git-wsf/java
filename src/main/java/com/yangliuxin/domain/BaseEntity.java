package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
abstract class BaseEntity<ID extends Serializable> implements Serializable {

	private static final long serialVersionUID = 2054813493011812469L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private ID id;

	@Column(name = "createTime")
	private Date createTime = new Date();

	@Column(name = "updateTime")
	private Date updateTime = new Date();

}
