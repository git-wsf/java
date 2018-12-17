package com.yangliuxin.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role_permission")
@Data
@EqualsAndHashCode
@IdClass(SysRolePermissionPK.class)
public class SysRolePermission implements Serializable {


    private static final long serialVersionUID = 5583231182173825915L;

    @Column(name = "permissionId")
    @Id
    private Long permissionId;

    @Column(name = "roleId")
    @Id
    private Long roleId;
}

class SysRolePermissionPK implements Serializable {


    @Column(name = "permissionId")
    @Basic
    private Long permissionId;

    @Column(name = "roleId")
    @Basic
    private Long roleId;
}
