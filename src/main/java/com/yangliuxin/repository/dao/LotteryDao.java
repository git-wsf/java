package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Lottery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface LotteryDao  extends JpaRepository<Lottery, Long>, JpaSpecificationExecutor<Lottery> {

}
