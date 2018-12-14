package com.yangliuxin.domain;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "sys_role_permission")
@Data
public class SysRolePermission {

    @Column(name = "permissionId")
    private Long permissionId;

    @Column(name = "roleId")
    private Long roleId;
}
