package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Dict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DictDao extends JpaRepository<Dict, Long>, JpaSpecificationExecutor<Dict> {



}


