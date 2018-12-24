package com.yangliuxin.repository.dao;

import com.yangliuxin.domain.Shop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface ShopDao  extends JpaRepository<Shop, Long>, JpaSpecificationExecutor<Shop> {

    Shop findFirstByShopIdAndDdd(String shopId, String ddd);

    List<Shop> findTop10ByDddOrderByDayCountryCountAsc(String ddd);

    List<Shop> findTop10ByDddOrderByDayProvinceCountAsc(String ddd);

    List<Shop> findTop10ByDddAndLevelOrderByWeekCountryCountAsc(String ddd, String level);

    List<Shop> findTop10ByDddAndProvinceAndLevelOrderByWeekProvinceCountAsc(String ddd, String province, String level);

    List<Shop> findTop10ByDddAndLevelOrderBySpringCountryCountAsc(String ddd, String level);

    List<Shop> findTop10ByDddAndProvinceAndLevelOrderBySpringProvinceCountAsc(String ddd, String province, String level);

    Shop getFirstByShopId(String shopId);


}
