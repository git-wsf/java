package com.yangliuxin.application;

import com.yangliuxin.model.Permission;

public interface PermissionService {

	void save(Permission permission);

	void update(Permission permission);

	void delete(Long id);
}
