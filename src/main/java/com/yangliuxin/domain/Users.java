package com.yangliuxin.domain;


import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "tb_users")
@Data
public class Users  extends BaseEntity<Long> {


    private static final long serialVersionUID = 7355939549099867356L;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "openId")
    private String openId;

    @Column(name = "createIp")
    private String createIp;

    @Column(name = "sex")
    private String sex;

    @Column(name = "province")
    private String province;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "headimgurl")
    private String headimgurl;


}
