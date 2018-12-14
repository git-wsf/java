package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Role;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RoleDao  extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {



}
