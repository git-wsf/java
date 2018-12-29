package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Notice;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.NoticeRepository;
import com.yangliuxin.repository.dao.NoticeDao;
import com.yangliuxin.vo.NoticeReadVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository
public class NoticeRepositoryImpl implements NoticeRepository {




    @Autowired
    private NoticeDao noticeDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Notice getById(Long id) {
        return noticeDao.getOne(id);
    }

    @Override
    public int delete(Long id) {
        noticeDao.delete(id);
        return 1;
    }

    @Override
    public int update(Notice notice) {
        noticeDao.save(notice);
        return 1;
    }

    @Override
    public int save(Notice notice) {
        noticeDao.save(notice);
        return 1;
    }


    private String getSqlByParamMap(Map<String, Object> params, String action, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        if(action.equals("count")){
            sb.append("select count(*) from tb_notice where 1 ");
        } else {
            sb.append("select * from tb_notice where 1 ");
        }
        if(params != null){
            for (String key : params.keySet()) {
                if (!StringUtils.isEmpty(params.get(key))) {
                    if(key.equals("title") && !StringUtils.isEmpty(params.get(key))){
                        sb.append(" and ").append(key).append(" like '%").append(params.get(key)).append("%'");
                    }
                    if(key.equals("beginTime") && !StringUtils.isEmpty(params.get(key))){
                        sb.append(" and beginTime >= '").append(params.get(key)).append("'");
                    }
                    if(key.equals("endTime") && !StringUtils.isEmpty(params.get(key))){
                        sb.append(" and endTime <= '").append(params.get(key)).append("'");
                    }
                    if(key.equals("status")){
                        sb.append(" and status = ").append(params.get(key));
                    }
                }
            }
            if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
                sb.append(" ").append(params.get("orderBy")).append(" ");
            }
        }

        if(offset != null && offset>=0 && limit != null && limit>0){
            sb.append(" limit ").append(offset);
        }

        if(limit != null && limit>=0){
            sb.append(",  ").append(limit);
        }
        return sb.toString();
    }

    @Override
    public int count(Map<String, Object> params) {
        String sql = getSqlByParamMap(params, "count",0,0);
        Query userQuery = entityManager.createNativeQuery(sql);
        return (Integer) Integer.parseInt(userQuery.getSingleResult().toString());
    }

    @Override
    public List<Notice> list(Map<String, Object> params, Integer offset, Integer limit) {
        String sql = getSqlByParamMap(params,"list",offset,limit);
        Query userQuery = entityManager.createNativeQuery(sql, Notice.class);
        return userQuery.getResultList();
    }

    @Override
    public int saveReadRecord(Long noticeId, Long userId) {
        return noticeDao.saveReadRecord(noticeId, userId);
    }

    @Override
    public List<SysUser> listReadUsers(Long noticeId) {
        return noticeDao.listReadUsers(noticeId);
    }

    @Override
    public int countUnread(Long userId) {
        return noticeDao.countUnread(userId);
    }


    @Override
    public int countNotice(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(1) from tb_notice t left join tb_notice_read r on r.noticeId = t.id and r.userId = ").append(params.get("userId"));
        sb.append(" where t.status = 1 ");
        if (!StringUtils.isEmpty(params.get("title"))) {
            sb.append(" and ").append(" t.title like '%").append(params.get("title")).append("%' ");
        }
        if (!StringUtils.isEmpty(params.get("beginTime"))) {
            sb.append(" and ").append(" t.updateTime >= '").append(params.get("beginTime")).append("' ");
        }
        if (!StringUtils.isEmpty(params.get("endTime"))) {
            sb.append(" and ").append(" t.updateTime <= '").append(params.get("endTime")).append("' ");
        }
        if (!StringUtils.isEmpty(params.get("isRead"))) {
            if(params.get("isRead").equals(0)){
                sb.append(" and ").append(" r.createTime is null ");
            }
            if(params.get("isRead").equals(1)){
                sb.append(" and ").append(" r.createTime is not null ");
            }

        }
        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, Notice.class);
        return (int)userQuery.getSingleResult();
    }

    @Override
    public List<NoticeReadVO> listNotice(Map<String, Object> params, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from tb_notice t left join tb_notice_read r on r.noticeId = t.id and r.userId = ").append(params.get("userId"));
        sb.append(" where t.status = 1 ");
        if (!StringUtils.isEmpty(params.get("title"))) {
            sb.append(" and ").append(" t.title like '%").append(params.get("title")).append("%' ");
        }
        if (!StringUtils.isEmpty(params.get("beginTime"))) {
            sb.append(" and ").append(" t.updateTime >= '").append(params.get("beginTime")).append("' ");
        }
        if (!StringUtils.isEmpty(params.get("endTime"))) {
            sb.append(" and ").append(" t.updateTime <= '").append(params.get("endTime")).append("' ");
        }
        if (!StringUtils.isEmpty(params.get("isRead"))) {
            if(params.get("isRead").equals(0)){
                sb.append(" and ").append(" r.createTime is null ");
            }
            if(params.get("isRead").equals(1)){
                sb.append(" and ").append(" r.createTime is not null ");
            }

        }
        if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
            sb.append(" ").append(params.get("orderBy")).append(" ");
        }
        if(offset>0){
            sb.append(" limit ").append(offset);
        }

        if(limit>0){
            sb.append(",  ").append(limit);
        }

        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, Notice.class);
        return userQuery.getResultList();
    }
}
