package com.yangliuxin.repository;

import com.yangliuxin.domain.Notice;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.vo.NoticeReadVO;

import java.util.List;
import java.util.Map;

public interface NoticeRepository {

    Notice getById(Long id);

    int delete(Long id);

    int update(Notice notice);

    int save(Notice notice);

    int count(Map<String, Object> params);

    List<Notice> list(Map<String, Object> params, Integer offset, Integer limit);

    int saveReadRecord(Long noticeId, Long userId);

    List<SysUser> listReadUsers(Long noticeId);

    int countUnread(Long userId);

    int countNotice(Map<String, Object> params);

    List<NoticeReadVO> listNotice(Map<String, Object> params, Integer offset, Integer limit);
}
