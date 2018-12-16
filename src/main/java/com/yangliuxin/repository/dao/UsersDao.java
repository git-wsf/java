package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UsersDao  extends JpaRepository<Users, Long>, JpaSpecificationExecutor<Users> {

    Users findByOpenId(String openId);
}
