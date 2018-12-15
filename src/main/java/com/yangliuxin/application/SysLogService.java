package com.yangliuxin.application;

import com.yangliuxin.domain.SysLogs;

public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
