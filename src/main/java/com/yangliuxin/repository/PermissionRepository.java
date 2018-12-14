package com.yangliuxin.repository;

import com.yangliuxin.domain.Permission;
import java.util.List;
import java.util.Set;

public interface PermissionRepository {

    List<Permission> listAll();

    List<Permission> listParents();

    List<Permission> listByUserId(Long userId);

    List<Permission> listByRoleId(Long roleId);

    Permission getById(Long id);

    int save(Permission permission);

    int update(Permission permission);

    int delete(Long id);

    int deleteByParentId(Long id);

    int deleteRolePermission(Long permissionId);

    Set<Long> listUserIds(Long permissionId);
}
