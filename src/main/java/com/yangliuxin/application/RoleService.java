package com.yangliuxin.application;

import com.yangliuxin.vo.RoleDto;

public interface RoleService {

	void saveRole(RoleDto roleDto) throws Exception;

	void deleteRole(Long id);
}
