package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Notice;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.NoticeRepository;
import com.yangliuxin.vo.NoticeReadVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


@Repository
public class NoticeRepositoryImpl implements NoticeRepository {
    @Override
    public Notice getById(Long id) {
        return null;
    }

    @Override
    public int delete(Long id) {
        return 0;
    }

    @Override
    public int update(Notice notice) {
        return 0;
    }

    @Override
    public int save(Notice notice) {
        return 0;
    }

    @Override
    public int count(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<Notice> list(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }

    @Override
    public int saveReadRecord(Long noticeId, Long userId) {
        return 0;
    }

    @Override
    public List<SysUser> listReadUsers(Long noticeId) {
        return null;
    }

    @Override
    public int countUnread(Long userId) {
        return 0;
    }

    @Override
    public int countNotice(Map<String, Object> params) {
        return 0;
    }

    @Override
    public List<NoticeReadVO> listNotice(Map<String, Object> params, Integer offset, Integer limit) {
        return null;
    }
}
