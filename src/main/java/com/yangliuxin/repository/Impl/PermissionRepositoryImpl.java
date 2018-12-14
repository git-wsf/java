package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Permission;
import com.yangliuxin.repository.PermissionRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;


@Repository
public class PermissionRepositoryImpl implements PermissionRepository {
    @Override
    public List<Permission> listAll() {
        return null;
    }

    @Override
    public List<Permission> listParents() {
        return null;
    }

    @Override
    public List<Permission> listByUserId(Long userId) {
        return null;
    }

    @Override
    public List<Permission> listByRoleId(Long roleId) {
        return null;
    }

    @Override
    public Permission getById(Long id) {
        return null;
    }

    @Override
    public int save(Permission permission) {
        return 0;
    }

    @Override
    public int update(Permission permission) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int deleteByParentId(Long id) {
        return 0;
    }

    @Override
    public int deleteRolePermission(Long permissionId) {
        return 0;
    }

    @Override
    public Set<Long> listUserIds(Long permissionId) {
        return null;
    }
}
