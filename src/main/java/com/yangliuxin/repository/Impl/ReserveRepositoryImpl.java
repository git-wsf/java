package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Reserve;
import com.yangliuxin.repository.ReserveRepository;
import com.yangliuxin.repository.dao.ReserveDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ReserveRepositoryImpl implements ReserveRepository {


    @Autowired
    private ReserveDao reserveDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Reserve save(Reserve reserve) {
        return reserveDao.save(reserve);
    }

    @Override
    public Reserve getById(Long id) {
        return reserveDao.getOne(id);
    }

    @Override
    public Long getCountByShopId(String shopId) {
        return reserveDao.countByShopId(shopId);
    }

    @Override
    public List<Reserve> getListByShopId(String shopId) {
        return reserveDao.getAllByShopId(shopId);
    }
}
