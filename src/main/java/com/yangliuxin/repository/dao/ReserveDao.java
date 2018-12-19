package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Reserve;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ReserveDao  extends JpaRepository<Reserve, Long>, JpaSpecificationExecutor<Reserve> {


    Long countByShopId(String shopId);

    List<Reserve> getAllByShopId(String shopId);

}
