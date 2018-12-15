package com.yangliuxin.application.Impl;

import com.yangliuxin.application.SysLogService;
import com.yangliuxin.domain.SysLogs;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.SysLogsRepository;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class SysLogServiceImpl implements SysLogService {

	private static final Logger log = LoggerFactory.getLogger("adminLogger");

	@Autowired
	private SysLogsRepository sysLogsRepository;

	@Async
	@Override
	public void save(SysLogs sysLogs) {

		if (sysLogs == null || sysLogs.getUserId() == null) {
			return;
		}
		sysLogsRepository.save(sysLogs);
	}

	@Async
	@Override
	public void save(Long userId, String module, Boolean flag, String remark) {
		SysLogs sysLogs = new SysLogs();
		sysLogs.setFlag(flag);
		sysLogs.setModule(module);
		sysLogs.setRemark(remark);

		SysUser user = new SysUser();
		user.setId(userId);
		sysLogs.setUserId(user.getId().intValue());

		sysLogsRepository.save(sysLogs);

	}

	@Override
	public void deleteLogs() {
		Date date = DateUtils.addMonths(new Date(), -3);
		String time = DateFormatUtils.format(date, DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT.getPattern());

		int n = sysLogsRepository.deleteLogs(time);
		log.info("删除{}之前日志{}条", time, n);
	}
}
