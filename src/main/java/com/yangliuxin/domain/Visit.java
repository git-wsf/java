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
@Table(name = "tb_visit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Visit  extends BaseEntity<Long> {

    private static final long serialVersionUID = -7624530838365028828L;

    @Column(name = "userId")
    private Integer userId;

    @Column(name = "shopId")
    private String shopId;
}
