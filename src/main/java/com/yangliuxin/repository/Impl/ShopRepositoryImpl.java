package com.yangliuxin.repository.Impl;

import com.yangliuxin.domain.Shop;
import com.yangliuxin.repository.ShopRepository;
import com.yangliuxin.repository.dao.ShopDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShopRepositoryImpl implements ShopRepository {




    @Autowired
    private ShopDao shopDao;


    @Autowired
    private EntityManager entityManager;

    @Override
    public Shop save(Shop shop) {
        return shopDao.save(shop);
    }

    @Override
    public Shop getShopData(String shopId, String ddd) {
        return shopDao.findFirstByShopIdAndDdd(shopId, ddd);
    }

    @Override
    public List<Shop> getTopShopData(Integer brand, String ddd, String province) {
        List<Shop> list = new ArrayList<>();
        if(brand == 1){
            list = shopDao.findTop10ByDddOrderByDayCountryCountAsc(ddd);
        }
        else if(brand == 2){
            list = shopDao.findTop10ByDddOrderByDayProvinceCountAsc(ddd);
        }
        else if(brand == 3){
            list = shopDao.findTop10ByDddOrderByWeekCountryCountAsc(ddd);
        }
        else if(brand == 4){
            list = shopDao.findTop10ByDddAndProvinceOrderByWeekProvinceCountAsc(ddd, province);
        }

        else if(brand == 6){
            list = shopDao.findTop10ByDddOrderBySpringCountryCountAsc(ddd);
        }
        else if(brand == 7){
            list = shopDao.findTop10ByDddAndProvinceOrderBySpringProvinceCountAsc(ddd, province);
        }
        return list;
    }

    @Override
    public Shop getStandardShopData(String shopId) {
        return shopDao.getFirstByShopId(shopId);
    }

}
