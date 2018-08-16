package com.test.demo.repository.dao;

import com.test.demo.domain.UserVersion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserVersionDao extends JpaRepository<UserVersion, Long> {



}