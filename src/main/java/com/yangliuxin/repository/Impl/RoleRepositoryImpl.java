package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Role;
import com.yangliuxin.domain.SysRolePermission;
import com.yangliuxin.domain.SysRoleUser;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.RoleRepository;
import com.yangliuxin.repository.dao.RoleDao;
import com.yangliuxin.repository.dao.SysRolePermissionDao;
import com.yangliuxin.repository.dao.SysRoleUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Repository
public class RoleRepositoryImpl implements RoleRepository {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private SysRoleUserDao sysRoleUserDao;

    @Autowired
    private SysRolePermissionDao sysRolePermissionDao;

    @Autowired
    private EntityManager entityManager;


    @Override
    public int save(Role role) {
        roleDao.save(role);
        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        String sql = getSqlByParamMap(params, "count", 0, 0);
        Query userQuery = entityManager.createNativeQuery(sql);
        return (Integer) Integer.parseInt(userQuery.getSingleResult().toString());
    }

    private String getSqlByParamMap(Map<String, Object> params, String action, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        if(action.equals("count")){
            sb.append("select count(*) from sys_role where 1 ");
        } else {
            sb.append("select * from sys_role where 1 ");
        }

        if(params != null){
            for (String key : params.keySet()) {
                if (!StringUtils.isEmpty(params.get(key))) {
                    if(key.equals("name") && !StringUtils.isEmpty(params.get(key))){
                        sb.append(" and ").append(key).append(" like '%").append(params.get(key)).append("%'");
                    }
                }
            }
            if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
                sb.append(" ").append(params.get("orderBy")).append(" ");
            }
        }


        if(offset != null && offset>0){
            sb.append(" limit ").append(offset);
        }

        if(limit != null && limit>0){
            sb.append(",  ").append(limit);
        }
        return sb.toString();
    }

    @Override
    public List<Role> list(Map<String, Object> params, Integer offset, Integer limit) {
        String sql = getSqlByParamMap(params,"list",offset,limit);
        Query userQuery = entityManager.createNativeQuery(sql,Role.class);
        return userQuery.getResultList();
    }

    @Override
    public Role getById(Long id) {
        return roleDao.getOne(id);
    }

    @Override
    public Role getRole(String name) {
        return roleDao.findByName(name);
    }

    @Override
    public int update(Role role) {
        roleDao.save(role);
        return 1;
    }

    @Override
    public List<Role> listByUserId(Long userId) {
        List<SysRoleUser> sysRoleUsers = sysRoleUserDao.findByUserId(userId);
        List<Long> userIds = new ArrayList<>();
        for(SysRoleUser sysRoleUser : sysRoleUsers){
            userIds.add(sysRoleUser.getRoleId());
        }
        String roleIds = userIds.stream()
                .map(Object::toString)
                .collect(Collectors.joining(","));
        return roleDao.findRolesByIds(roleIds);
    }

    @Override
    public int deleteRolePermission(Long roleId) {
        sysRolePermissionDao.deleteByRoleId(roleId);
        return 1;
    }

    @Override
    public int saveRolePermission(Long roleId, List<Long> permissionIds) {
        for (Long permissionId: permissionIds ) {
            SysRolePermission sysRolePermission = new SysRolePermission();
            sysRolePermission.setPermissionId(permissionId);
            sysRolePermission.setRoleId(roleId);
            sysRolePermissionDao.save(sysRolePermission);

        }
        return 1;
    }

    @Override
    public int delete(Long id) {
        roleDao.delete(id);
        return 1;
    }

    @Override
    public int deleteRoleUser(Long roleId) {

        return sysRoleUserDao.deleteByRoleId(roleId);
    }
}
