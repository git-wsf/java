package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Notice;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface NoticeDao  extends JpaRepository<Notice,Long>, JpaSpecificationExecutor<Notice> {


}
