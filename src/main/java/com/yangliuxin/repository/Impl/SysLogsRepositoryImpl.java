package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.SysLogs;
import com.yangliuxin.domain.SysUser;
import com.yangliuxin.repository.SysLogsRepository;
import com.yangliuxin.repository.dao.SysLogsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository
public class SysLogsRepositoryImpl implements SysLogsRepository {


    @Autowired
    private SysLogsDao sysLogsDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public int save(SysLogs sysLogs) {
        sysLogsDao.save(sysLogs);
        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        String sql = getSqlByParamMap(params, "count");
        Query userQuery = entityManager.createNativeQuery(sql, SysUser.class);
        return (Integer)userQuery.getSingleResult();
    }

    private String getSqlByParamMap(Map<String, Object> params, String action) {
        StringBuilder sb = new StringBuilder();
        if(action.equals("count")){
            sb.append("select count(*) from sys_logs where 1 ");
        } else {
            sb.append("select * from sys_logs where 1 ");
        }

        for (String key : params.keySet()) {
            if (!StringUtils.isEmpty(params.get(key))) {
                if(key.equals("flag") && !StringUtils.isEmpty(params.get(key))){
                    sb.append(" and ").append(key).append(" = '").append(params.get(key)).append("'");
                }
                if(key.equals("nickname") && !StringUtils.isEmpty(params.get(key))){
                    sb.append(" and ").append(key).append(" like '%").append(params.get(key)).append("%'");
                }
                if(key.equals("beginTime") && !StringUtils.isEmpty(params.get(key))){
                    sb.append(" and beginTime >= '").append(params.get(key)).append("'");
                }
                if(key.equals("endTime") && !StringUtils.isEmpty(params.get(key))){
                    sb.append(" and endTime <= '").append(params.get(key)).append("'");
                }

            }
        }
        if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
            sb.append(" ").append(params.get("orderBy")).append(" ");
        }
        if(params.containsKey("offset") && params.containsKey("limit") && !StringUtils.isEmpty(params.get("offset")) && !StringUtils.isEmpty(params.get("limit"))){
            sb.append(" limit ").append(params.get("offset")).append(",  ").append("limit");
        }
        return sb.toString();
    }

    @Override
    public List<SysLogs> list(Map<String, Object> params, Integer offset, Integer limit) {
        String sql = getSqlByParamMap(params,"list");
        Query userQuery = entityManager.createNativeQuery(sql, SysUser.class);
        return userQuery.getResultList();
    }

    @Override
    public int deleteLogs(String time) {
        String sql = "delete from sys_logs where createTime <= '"+time+"'";
        Query userQuery = entityManager.createNativeQuery(sql, SysUser.class);
        return userQuery.executeUpdate();
    }
}
