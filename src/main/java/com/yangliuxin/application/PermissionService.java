package com.yangliuxin.application;

import com.yangliuxin.domain.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
