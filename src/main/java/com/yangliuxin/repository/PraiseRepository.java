package com.yangliuxin.repository;

import com.yangliuxin.domain.Praise;

public interface PraiseRepository {

    Praise save(Praise praise);

    Long getCountByShopId(String shopId);

}
