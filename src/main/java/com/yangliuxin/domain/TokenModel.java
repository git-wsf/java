package com.yangliuxin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "tb_token")
@Data
@EqualsAndHashCode(callSuper =false)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@DynamicInsert
@DynamicUpdate
public class TokenModel extends BaseEntity<String> {

	private static final long serialVersionUID = 4566334160572911795L;

	@Column(name = "expireTime")
	private Date expireTime;

	@Column(name = "val")
	private String val;

}
