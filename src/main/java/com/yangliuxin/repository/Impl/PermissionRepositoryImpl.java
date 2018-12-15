package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Permission;
import com.yangliuxin.repository.PermissionRepository;
import com.yangliuxin.repository.dao.PermissionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;


@Repository
public class PermissionRepositoryImpl implements PermissionRepository {



    @Autowired
    private PermissionDao permissionDao;


    @Autowired
    private EntityManager entityManager;



    @Override
    public List<Permission> listAll() {
        Sort sort = new Sort(new Sort.Order(Sort.Direction.ASC,"sort"));
        return permissionDao.findAll(sort);
    }

    @Override
    public List<Permission> listParents() {
        return permissionDao.findAllByTypeOrderBySortAsc(1);
    }

    @Override
    public List<Permission> listByUserId(Long userId) {
        return permissionDao.listByUserId(userId);
    }

    @Override
    public List<Permission> listByRoleId(Long roleId) {
        return permissionDao.listByRoleId(roleId);
    }

    @Override
    public Permission getById(Long id) {
        return permissionDao.getOne(id);
    }

    @Override
    public int save(Permission permission) {
        permissionDao.save(permission);
        return 1;
    }

    @Override
    public int update(Permission permission) {
        permissionDao.save(permission);
        return 1;
    }

    @Override
    public int delete(Long id) {
        permissionDao.delete(id);
        return 1;
    }

    @Override
    public int deleteByParentId(Long id) {
        permissionDao.deleteByParentId(id);
        return 1;
    }

    @Override
    public int deleteRolePermission(Long permissionId) {
        return permissionDao.deleteRolePermission(permissionId);
    }

    @Override
    public Set<Long> listUserIds(Long permissionId) {
        return permissionDao.listUserIds(permissionId);
    }
}
