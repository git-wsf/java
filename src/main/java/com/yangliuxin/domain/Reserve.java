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
@Table(name = "tb_reserve")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reserve  extends BaseEntity<Long>{


    private static final long serialVersionUID = -3994457090449816862L;
    @Column(name = "shopId")
    private String shopId;

    @Column(name = "shop")
    private String shop;

    @Column(name = "product")
    private String product;

    @Column(name = "name")
    private String name;

    @Column(name = "mobile")
    private String mobile;

}
