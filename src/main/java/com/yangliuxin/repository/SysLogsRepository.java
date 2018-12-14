package com.yangliuxin.repository;

import com.yangliuxin.domain.SysLogs;

import java.util.List;
import java.util.Map;

public interface SysLogsRepository {

    int save(SysLogs sysLogs);

    int count(Map<String, Object> params);

    List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit);

    int deleteLogs(String time);
}
