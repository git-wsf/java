package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

public interface PermissionDao  extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {


    List<Permission> findAllByTypeOrderBySortAsc(Integer type);


    @Query(value = "select distinct p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId inner join sys_role_user ru on ru.roleId = rp.roleId where ru.userId = ?1 order by p.sort", nativeQuery = true)
    List<Permission> listByUserId(Long userId);

    @Query(value = "select p.* from sys_permission p inner join sys_role_permission rp on p.id = rp.permissionId where rp.roleId = ?1 order by p.sort", nativeQuery = true)
    List<Permission> listByRoleId(Long roleId);


    @Modifying
    @Transactional
    @Query(value = "delete from sys_permission where parentId = ?1", nativeQuery = true)
    int deleteByParentId(Long id);

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role_permission where permissionId = ?1", nativeQuery = true)
    int deleteRolePermission(Long permissionId);

    @Query(value = "select ru.userId from sys_role_permission rp inner join sys_role_user ru on ru.roleId = rp.roleId where rp.permissionId = ?1", nativeQuery = true)
    Set<Long> listUserIds(Long permissionId);
}
