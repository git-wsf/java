package com.yangliuxin.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_shop")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Shop  extends BaseEntity<Long> {

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "shopName")
    private String shopName;

    @Column(name = "level")
    private String level;

    @Column(name = "address")
    private String address;

    @Column(name = "day")
    private Integer day;

    @Column(name = "dayCountryCount")
    private Integer dayCountryCount;

    @Column(name = "dayProvinceCount")
    private Integer dayProvinceCount;

    @Column(name = "week")
    private Integer week;

    @Column(name = "weekCountryCount")
    private Integer weekCountryCount;

    @Column(name = "weekProvinceCount")
    private Integer weekProvinceCount;

    @Column(name = "weekLevelCount")
    private String weekLevelCount;

    @Column(name = "spring")
    private Integer spring;

    @Column(name = "springCountryCount")
    private Integer springCountryCount;

    @Column(name = "springProvinceCount")
    private Integer springProvinceCount;

    @Column(name = "springLevelCount")
    private Integer springLevelCount;

    @Column(name = "percent")
    private String percent;

    @Column(name = "ddd")
    private String ddd;

}
