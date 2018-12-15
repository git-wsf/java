package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysRolePermissionDao   extends JpaRepository<SysRolePermission, Long>, JpaSpecificationExecutor<SysRolePermission> {
}
