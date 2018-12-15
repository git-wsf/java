package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoleDao  extends JpaRepository<Role, Long>, JpaSpecificationExecutor<Role> {

    Role findByName(String name);


    @Query(value = "select * from sys_role where id in (?1)", nativeQuery = true)
    List<Role> findRolesByIds(String roleIds);

}
