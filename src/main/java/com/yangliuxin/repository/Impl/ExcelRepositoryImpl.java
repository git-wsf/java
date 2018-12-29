package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Excel;
import com.yangliuxin.repository.ExcelRepository;
import com.yangliuxin.repository.dao.ExcelDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;

@Repository
public class ExcelRepositoryImpl implements ExcelRepository {
    @Autowired
    private ExcelDao excelDao;


    @Autowired
    private EntityManager entityManager;


    @Override
    public Excel getById(String id) {
        return excelDao.findById(id);
    }

    @Override
    public int save(Excel Excel) {
        excelDao.save(Excel);
        return 1;
    }

    @Override
    public int update(Excel Excel) {
        excelDao.save(Excel);
        return 1;
    }

    @Override
    public int delete(String id) {
        excelDao.delete(Long.parseLong(id));
        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        return ((int) (excelDao.count()));
    }

    @Override
    public List<Excel> list(Map<String, Object> params, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from tb_excel t ");

        if(params != null && params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
            sb.append(" ").append(params.get("orderBy")).append(" ");
        }
        if(offset != null && offset>=0 && limit != null && limit>0){
            sb.append(" limit ").append(offset);
        }

        if(limit != null && limit>0){
            sb.append(",  ").append(limit);
        }

        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, Excel.class);
        return userQuery.getResultList();
    }
}
