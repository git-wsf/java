package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.TokenModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TokenDao extends JpaRepository<TokenModel, Long>, JpaSpecificationExecutor<TokenModel>{

}

