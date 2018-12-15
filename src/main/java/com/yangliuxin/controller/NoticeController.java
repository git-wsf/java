package com.yangliuxin.controller;

import com.yangliuxin.annotation.LogAnnotation;
import com.yangliuxin.domain.Notice;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.enums.NoticeStatusEnum;
import com.yangliuxin.page.PageTableHandler;
import com.yangliuxin.page.PageTableHandler.CountHandler;
import com.yangliuxin.page.PageTableHandler.ListHandler;
import com.yangliuxin.page.PageTableRequest;
import com.yangliuxin.page.PageTableResponse;
import com.yangliuxin.repository.NoticeRepository;
import com.yangliuxin.utils.UserUtil;
import com.yangliuxin.vo.NoticeReadVO;
import com.yangliuxin.vo.NoticeVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = "公告")
@RestController
@RequestMapping("/notices")
public class NoticeController {

	@Autowired
	private NoticeRepository noticeRepository;

	@LogAnnotation
	@PostMapping
	@ApiOperation(value = "保存公告")
	@PreAuthorize("hasAuthority('notice:add')")
	public Notice saveNotice(@RequestBody Notice notice) {
		noticeRepository.save(notice);

		return notice;
	}

	@GetMapping("/{id}")
	@ApiOperation(value = "根据id获取公告")
	@PreAuthorize("hasAuthority('notice:query')")
	public Notice get(@PathVariable Long id) {
		return noticeRepository.getById(id);
	}

	@GetMapping(params = "id")
	public NoticeVO readNotice(Long id) {
		NoticeVO vo = new NoticeVO();

		Notice notice = noticeRepository.getById(id);
		if (notice == null || notice.getStatus() == NoticeStatusEnum.DRAFT.getValue()) {
			return vo;
		}
		vo.setNotice(notice);

		noticeRepository.saveReadRecord(notice.getId(), UserUtil.getLoginUser().getId());

		List<SysUser> users = noticeRepository.listReadUsers(id);
		vo.setUsers(users);

		return vo;
	}

	@LogAnnotation
	@PutMapping
	@ApiOperation(value = "修改公告")
	@PreAuthorize("hasAuthority('notice:add')")
	public Notice updateNotice(@RequestBody Notice notice) {
		Notice no = noticeRepository.getById(notice.getId());
		if (no.getStatus() == NoticeStatusEnum.PUBLISH.getValue()) {
			throw new IllegalArgumentException("发布状态的不能修改");
		}
		noticeRepository.update(notice);

		return notice;
	}

	@GetMapping
	@ApiOperation(value = "公告管理列表")
	@PreAuthorize("hasAuthority('notice:query')")
	public PageTableResponse listNotice(PageTableRequest request) {
		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return noticeRepository.count(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<Notice> list(PageTableRequest request) {
				return noticeRepository.list(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}

	@LogAnnotation
	@DeleteMapping("/{id}")
	@ApiOperation(value = "删除公告")
	@PreAuthorize("hasAuthority('notice:del')")
	public void delete(@PathVariable Long id) {
		noticeRepository.delete(id);
	}

	@ApiOperation(value = "未读公告数")
	@GetMapping("/count-unread")
	public Integer countUnread() {
		SysUser user = UserUtil.getLoginUser();
		return noticeRepository.countUnread(user.getId());
	}

	@GetMapping("/published")
	@ApiOperation(value = "公告列表")
	public PageTableResponse listNoticeReadVO(PageTableRequest request) {
		request.getParams().put("userId", UserUtil.getLoginUser().getId());

		return new PageTableHandler(new CountHandler() {

			@Override
			public int count(PageTableRequest request) {
				return noticeRepository.countNotice(request.getParams());
			}
		}, new ListHandler() {

			@Override
			public List<NoticeReadVO> list(PageTableRequest request) {
				return noticeRepository.listNotice(request.getParams(), request.getOffset(), request.getLimit());
			}
		}).handle(request);
	}
}
