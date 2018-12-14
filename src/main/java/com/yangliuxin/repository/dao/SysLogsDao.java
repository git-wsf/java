package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.SysLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface SysLogsDao  extends JpaRepository<SysLogs, Long>, JpaSpecificationExecutor<SysLogs> {


}
