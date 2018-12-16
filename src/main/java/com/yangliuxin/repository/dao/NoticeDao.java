package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Notice;
import com.yangliuxin.domain.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface NoticeDao  extends JpaRepository<Notice,Long>, JpaSpecificationExecutor<Notice> {

    @Query(value = "insert ignore into tb_notice_read(noticeId, userId, createTime) values('?1', '?2', now())", nativeQuery = true)
    @Modifying
    @Transactional
    int saveReadRecord(Long noticeId, Long userId);


    @Query(value = "select u.* from tb_notice_read r inner join sys_user u on u.id = r.userId where r.noticeId = ?1 order by r.createTime desc", nativeQuery = true)
    List<SysUser> listReadUsers(Long noticeId);

    @Query(value = "select count(1) from tb_notice t left join tb_notice_read r on r.noticeId = t.id and r.userId = ?1 where t.status = 1 and r.userId is null", nativeQuery = true)
    int countUnread(Long userId);



}
