package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysLogs;
import com.yangliuxin.domain.SysRoleUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface SysRoleUserDao  extends JpaRepository<SysRoleUser, Long>, JpaSpecificationExecutor<SysRoleUser> {

    @Modifying
    @Transactional
    @Query("delete from sys_role_user where userId = ?1")
    int deleteByUserId(Long userId);


}
