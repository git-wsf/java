package com.test.demo.repository.dao;

import com.test.demo.domain.User;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

public interface UserDao extends JpaRepository<User, Long> , JpaSpecificationExecutor<User>  {

    List<User> findAll(Specification<User> var1);


}
