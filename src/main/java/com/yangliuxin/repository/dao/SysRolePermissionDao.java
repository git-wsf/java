package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysRolePermission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SysRolePermissionDao   extends JpaRepository<SysRolePermission, Long>, JpaSpecificationExecutor<SysRolePermission> {

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role_permission  where roleId = ?1", nativeQuery = true)
    int deleteByRoleId(Long roleId);
}
