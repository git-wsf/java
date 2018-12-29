package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Excel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ExcelDao extends JpaRepository<Excel, Long>, JpaSpecificationExecutor<Excel> {



    Excel findById(String id);
}
