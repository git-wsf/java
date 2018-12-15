package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserDao extends JpaRepository<SysUser, Long> , JpaSpecificationExecutor<SysUser>  {

    SysUser findByUsername(String userName);

}
