package com.yangliuxin.domain;


import com.yangliuxin.vo.RoleDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity<Long> {

	private static final long serialVersionUID = -3802292814767103648L;

	@Column(name = "name")
	private String name;

	@Column(name = "description")
	private String description;

	public Role(RoleDto roleDto) {

		name = roleDto.getName();
		description = roleDto.getDescription();
		setId(roleDto.getId());
	}
}
