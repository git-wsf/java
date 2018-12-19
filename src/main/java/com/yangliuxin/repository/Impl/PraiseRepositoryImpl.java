package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Praise;
import com.yangliuxin.repository.PraiseRepository;
import com.yangliuxin.repository.dao.PraiseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;

@Repository
public class PraiseRepositoryImpl implements PraiseRepository {


    @Autowired
    private EntityManager entityManager;

    @Autowired
    private PraiseDao praiseDao;

    @Override
    public Praise save(Praise praise) {
        return praiseDao.save(praise);
    }

    @Override
    public Long getCountByShopId(String shopId) {
        return praiseDao.countAllByShopId(shopId);
    }
}
