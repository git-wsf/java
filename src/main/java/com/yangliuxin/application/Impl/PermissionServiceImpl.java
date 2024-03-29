package com.yangliuxin.application.Impl;

import com.yangliuxin.application.PermissionService;
import com.yangliuxin.domain.Permission;
import com.yangliuxin.repository.PermissionRepository;
import com.yangliuxin.repository.dao.PermissionDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PermissionServiceImpl implements PermissionService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void save(Permission permission) {
		permissionRepository.save(permission);

		log.debug("新增菜单{}", permission.getName());
	}

	@Override
	public void update(Permission permission) {
		permissionRepository.update(permission);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		permissionRepository.deleteRolePermission(id);
		permissionRepository.delete(id);
		permissionRepository.deleteByParentId(id);

		log.debug("删除菜单id:{}", id);
	}

}
