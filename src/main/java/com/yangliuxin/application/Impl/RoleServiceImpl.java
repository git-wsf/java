package com.yangliuxin.application.Impl;

import com.yangliuxin.application.RoleService;
import com.yangliuxin.domain.Role;
import com.yangliuxin.repository.RoleRepository;
import com.yangliuxin.vo.RoleDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private RoleRepository roleRepository;

	@Override
	@Transactional
	public void saveRole(RoleDto roleDto) {
		Role role = roleDto;
		List<Long> permissionIds = roleDto.getPermissionIds();
		permissionIds.remove(0L);

		if (role.getId() != null) {// 修改
			updateRole(role, permissionIds);
		} else {// 新增
			saveRole(role, permissionIds);
		}
	}

	private void saveRole(Role role, List<Long> permissionIds) {
		Role r = roleRepository.getRole(role.getName());
		if (r != null) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}

		roleRepository.save(role);
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleRepository.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("新增角色{}", role.getName());
	}

	private void updateRole(Role role, List<Long> permissionIds) {
		Role r = roleRepository.getRole(role.getName());
		if (r != null && r.getId() != role.getId()) {
			throw new IllegalArgumentException(role.getName() + "已存在");
		}

		roleRepository.update(role);

		roleRepository.deleteRolePermission(role.getId());
		if (!CollectionUtils.isEmpty(permissionIds)) {
			roleRepository.saveRolePermission(role.getId(), permissionIds);
		}
		log.debug("修改角色{}", role.getName());
	}

	@Override
	@Transactional
	public void deleteRole(Long id) {
		roleRepository.deleteRolePermission(id);
		roleRepository.deleteRoleUser(id);
		roleRepository.delete(id);

		log.debug("删除角色id:{}", id);
	}

}
