package com.yangliuxin.repository;

import com.yangliuxin.domain.Role;

import java.util.List;
import java.util.Map;

public interface RoleRepository {

    int save(Role role);

    int count(Map<String, Object> params);

    List<Role> list(Map<String, Object> params, Integer offset, Integer limit);

    Role getById(Long id);

    Role getRole(String name);

    int update(Role role);

    List<Role> listByUserId(Long userId);

    int deleteRolePermission(Long roleId);

    int saveRolePermission(Long roleId, List<Long> permissionIds);

    int delete(Long id);

    int deleteRoleUser(Long roleId);
}
