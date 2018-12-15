package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
public abstract class BaseEntity<ID extends Serializable> implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private ID id;

	@Column(name = "createTime")
	private Date createTime = new Date();

	@Column(name = "updateTime")
	private Date updateTime = new Date();

}
