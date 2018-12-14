package com.yangliuxin.application;

import com.yangliuxin.model.SysLogs;

/**
 * 日志service
 * 
 * @author Listen Young lxyang_21@163.com
 *
 *         2017年8月19日
 */
public interface SysLogService {

	void save(SysLogs sysLogs);

	void save(Long userId, String module, Boolean flag, String remark);

	void deleteLogs();
}
