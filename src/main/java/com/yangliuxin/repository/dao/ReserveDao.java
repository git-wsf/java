package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ReserveDao  extends JpaRepository<Reserve, Long>, JpaSpecificationExecutor<Reserve> {

}
