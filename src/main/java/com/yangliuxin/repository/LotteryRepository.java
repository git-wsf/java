package com.yangliuxin.repository;

import com.yangliuxin.domain.Lottery;

public interface LotteryRepository {
    Lottery save(Lottery lottery);

    Lottery getById(Long id);
}
