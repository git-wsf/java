package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Dict;
import com.yangliuxin.domain.Notice;
import com.yangliuxin.repository.DictRepository;
import com.yangliuxin.repository.dao.DictDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Map;


@Repository
public class DictRepositoryImpl implements DictRepository {

    @Autowired
    private DictDao dictDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Dict getById(Long id) {
        return dictDao.getOne(id);
    }

    @Override
    public int delete(Long id) {
        dictDao.delete(id);
        return 1;
    }

    @Override
    public int update(Dict dict) {
        dictDao.save(dict);
        return 1;
    }

    @Override
    public int save(Dict dict) {
        dictDao.save(dict);
        return 1;
    }

    @Override
    public int count(Map<String, Object> params) {
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from tb_dict t where 1 ");

        if(params != null){
            if (!StringUtils.isEmpty(params.get("id"))) {
                sb.append(" and ").append(" t.id = ").append(params.get("id "));
            }
            if (!StringUtils.isEmpty(params.get("type"))) {
                sb.append(" and ").append(" t.type = ").append(params.get("type "));
            }
            if (!StringUtils.isEmpty(params.get("k"))) {
                sb.append(" and ").append(" t.k = ").append(params.get("k "));
            }
            if (!StringUtils.isEmpty(params.get("val"))) {
                sb.append(" and ").append(" t.val = ").append(params.get("val "));
            }
            if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
                sb.append(" and ").append(" t.type = ").append(params.get("type "));
            }
        }


        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql);
        return  Integer.parseInt(userQuery.getSingleResult().toString());
    }

    @Override
    public List<Dict> list(Map<String, Object> params, Integer offset, Integer limit) {
        StringBuilder sb = new StringBuilder();
        sb.append("select * from tb_dict t where 1 ");
        if(params != null){
            if (!StringUtils.isEmpty(params.get("id"))) {
                sb.append(" and ").append(" t.id = ").append(params.get("id "));
            }
            if (!StringUtils.isEmpty(params.get("type"))) {
                sb.append(" and ").append(" t.type = ").append(params.get("type "));
            }
            if (!StringUtils.isEmpty(params.get("k"))) {
                sb.append(" and ").append(" t.k = ").append(params.get("k "));
            }
            if (!StringUtils.isEmpty(params.get("val"))) {
                sb.append(" and ").append(" t.val = ").append(params.get("val "));
            }
            if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
                sb.append(" and ").append(" t.type = ").append(params.get("type "));
            }
            if(params.containsKey("orderBy") && !StringUtils.isEmpty(params.get("orderBy"))){
                sb.append(" ").append(params.get("orderBy")).append(" ");
            }
        }

        if(offset != null && offset>=0 && limit != null && limit>0){
            sb.append(" limit ").append(offset);
        }

        if(limit != null && limit>0){
            sb.append(",  ").append(limit);
        }

        String sql = sb.toString();
        Query userQuery = entityManager.createNativeQuery(sql, Dict.class);
        return userQuery.getResultList();
    }

    @Override
    public Dict getByTypeAndK(String type, String k) {
        return dictDao.getByTypeAndK(type, k);
    }

    @Override
    public List<Dict> listByType(String type) {
        return dictDao.listByType(type);
    }
}
