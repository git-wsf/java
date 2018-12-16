package com.yangliuxin.repository;

import com.yangliuxin.domain.Shop;

import java.util.List;

public interface ShopRepository {


    Shop save(Shop shop);

    Shop getShopData(Integer shopId, String ddd);

    List<Shop> getTopShopData(Integer brand, String ddd);

}
