package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface LotteryDao  extends JpaRepository<Lottery, Long>, JpaSpecificationExecutor<Lottery> {

    List<Lottery> getAllByShopIdOrderByIdDesc(String shopId);

}
