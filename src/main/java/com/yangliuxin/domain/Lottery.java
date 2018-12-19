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
@Table(name = "tb_lottery")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Lottery  extends BaseEntity<Long> {


    private static final long serialVersionUID = -2989419297411564032L;

    @Column(name = "ind")
    private Integer index;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "name")
    private String name;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @Column(name = "shopId")
    private String shopId;

}
