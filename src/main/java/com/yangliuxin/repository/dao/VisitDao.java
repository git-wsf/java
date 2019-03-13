package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface VisitDao  extends JpaRepository<Visit, Long>, JpaSpecificationExecutor<Visit> {

}
