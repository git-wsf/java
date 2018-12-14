package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Role;
import com.yangliuxin.repository.RoleRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class RoleRepositoryImpl implements RoleRepository {
    @Override
    public int save(Role role) {
        return 0;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<Role> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public Role getById(Long id) {
        return null;
    }

    @Override
    public Role getRole(String name) {
        return null;
    }

    @Override
    public int update(Role role) {
        return 0;
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        return null;
    }

    @Override
    public int deleteRolePermission(Long roleId) {
        return 0;
    }

    @Override
    public int saveRolePermission(Long roleId, List<Long> permissionIds) {
        return 0;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int deleteRoleUser(Long roleId) {
        return 0;
    }
}
