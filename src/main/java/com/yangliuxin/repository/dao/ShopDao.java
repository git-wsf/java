package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopDao  extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {

    Shop findFirstByShopIdAndDdd(String shopId, String ddd);

    List<Shop> findTop10ByDddOrderByDayCountryCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderByDayProvinceCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderByWeekCountryCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderByWeekProvinceCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderBySpringCountryCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderBySpringProvinceCountAsc(String ddd);

    Shop getFirstByShopId(String shopId);


}
