package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Lottery;
import com.yangliuxin.repository.LotteryRepository;
import com.yangliuxin.repository.dao.LotteryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LotteryRepositoryImpl implements LotteryRepository {


    @Autowired
    private LotteryDao lotteryDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Lottery save(Lottery lottery) {
        return lotteryDao.save(lottery);
    }

    @Override
    public Lottery getById(Long id) {
        return lotteryDao.getOne(id);
    }

    @Override
    public List<Lottery> getListByShopId(String shopId) {
        return lotteryDao.getAllByShopId(shopId);
    }

    @Override
    public List<Lottery> getAllList() {
        return lotteryDao.findAll();
    }
}
