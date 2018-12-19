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
@Table(name = "tb_praise")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Praise extends BaseEntity<Long> {

    @Column(name = "shopId")
    private String shopId;

    @Column(name = "userId")
    private Integer userId;
}
