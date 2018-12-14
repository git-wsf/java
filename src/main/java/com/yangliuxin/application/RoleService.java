package com.yangliuxin.application;

import com.yangliuxin.vo.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto);

	void deleteRole(Long id);
}
