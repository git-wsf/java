package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.SysLogs;
import com.yangliuxin.repository.SysLogsRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class SysLogsRepositoryImpl implements SysLogsRepository {
    @Override
    public int save(SysLogs sysLogs) {
        return 0;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public int deleteLogs(String time) {
        return 0;
    }
}
