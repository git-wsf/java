package com.yangliuxin.controller;

import com.yangliuxin.domain.SysLogs;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableHandler.CountHandler;
import com.yangliuxin.page.PageTableHandler.ListHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.SysLogsRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Api(tags = "日志")
@RestController
@RequestMapping("/logs")
public class SysLogsController {

	@Autowired
	private SysLogsRepository sysLogsRepository;

	@GetMapping
	@PreAuthorize("hasAuthority('sys:log:query')")
	@ApiOperation(value = "日志列表")
	public PageTableResponse list(PageTableRequest request) {

		CountHandler countHandler = new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return sysLogsRepository.count(request.getParams());
			}
		};
		ListHandler listHandler = new ListHandler() {

			@Override
			public List<SysLogs> list(PageTableRequest request) {
				return sysLogsRepository.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		};
		PageTableHandler pageTableHandler = new PageTableHandler(countHandler, listHandler);

		PageTableResponse pageTableResponse =  pageTableHandler.handle(request);
		return pageTableResponse;
	}

}
