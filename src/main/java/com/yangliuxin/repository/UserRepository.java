package com.yangliuxin.repository;

import com.yangliuxin.domain.SysUser;

import java.util.List;
import java.util.Map;

public interface UserRepository  {

    int save(SysUser user);

    SysUser getById(Long id);

    SysUser getUser(String username);

    int changePassword(Long id, String password);

    Integer count(Map<String, Object> params);

    List<SysUser> list(Map<String, Object> params, Integer offset, Integer limit);

    int deleteUserRole(Long userId);

    int saveUserRoles(Long userId, List<Long> roleIds);

    int update(SysUser user);

}
