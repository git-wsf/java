package com.yangliuxin.repository;

import com.yangliuxin.domain.Reserve;

import java.util.List;

public interface ReserveRepository {

    Reserve save(Reserve reserve);

    Reserve getById(Long id);

    Long getCountByShopId(String shopId);

    List<Reserve> getListByShopId(String shopId);
}
