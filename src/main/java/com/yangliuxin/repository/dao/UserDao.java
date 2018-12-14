package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>  {

    List<User> findAll(Specification<User> var1);


}
