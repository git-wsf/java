package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface SysRoleUserDao  extends JpaRepository<SysRoleUser, Long>, JpaSpecificationExecutor<SysRoleUser> {

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role_user where userId = ?1",nativeQuery = true)
    int deleteByUserId(Long userId);


    List<SysRoleUser> findByUserId(Long userId);

    @Modifying
    @Transactional
    @Query(value = "delete from sys_role_user where uroleId = ?1",nativeQuery = true)
    int deleteByRoleId(Long roleId);

}
