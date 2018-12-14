package com.yangliuxin.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_user")
@Data
public class SysRoleUser {

    @Column(name = "userId")
    private Long userId;

    @Column(name = "roleId")
    private Long roleId;
}
