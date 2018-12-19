package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Praise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PraiseDao   extends JpaRepository<Praise, Long>, JpaSpecificationExecutor<Praise> {

    Long countAllByShopId(String shopId);
}
