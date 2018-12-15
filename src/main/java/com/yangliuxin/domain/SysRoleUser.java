package com.yangliuxin.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sys_role_user")
@Data
@EqualsAndHashCode
@IdClass(SysRoleUserPK.class)
public class SysRoleUser implements Serializable {

    private static final long serialVersionUID = -6202660622896176982L;

    @Column(name = "userId")
    @Id
    private Long userId;

    @Column(name = "roleId")
    @Id
    private Long roleId;
}

@Data
class SysRoleUserPK implements Serializable {

    @Column(name = "userId")
    @Basic
    private Long userId;

    @Column(name = "roleId")
    @Basic
    private Long roleId;

}
