package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionDao  extends JpaRepository<Permission, Long>, JpaSpecificationExecutor<Permission> {


}
