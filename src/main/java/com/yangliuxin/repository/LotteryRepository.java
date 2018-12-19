package com.yangliuxin.repository;

import com.yangliuxin.domain.Lottery;

import java.util.List;

public interface LotteryRepository {
    Lottery save(Lottery lottery);

    Lottery getById(Long id);

    List<Lottery> getListByShopId(String shopId);
}
