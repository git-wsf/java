package com.yangliuxin.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "sys_user")
@Data
public class SysUser extends BaseEntity<Long> {

	private static final long serialVersionUID = -6525908145032868837L;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "nickname")
	private String nickname;

	@Column(name = "headImgUrl")
	private String headImgUrl;

	@Column(name = "phone")
	private String phone;

	@Column(name = "telephone")
	private String telephone;

	@Column(name = "email")
	private String email;

	@Column(name = "birthday")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthday;

	@Column(name = "sex")
	private Integer sex;

	@Column(name = "status")
	private Integer status;

	@Column(name = "intro")
	private String intro;


}
