package com.yangliuxin.domain;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Version;
import java.time.Instant;


@MappedSuperclass
@Data
public abstract class AbstractDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Version
    @Column(name = "version",nullable = false)
    protected Integer version;

    @Column(name = "create_time",nullable = false,updatable = false)
    protected Long createTime;

    @Column(name = "update_time",nullable = false)
    protected Long updateTime;


    @PrePersist
    public void prePersist() {
        this.setCreateTime(Instant.now().getEpochSecond());
        this.setUpdateTime(Instant.now().getEpochSecond());
    }

    @PreUpdate
    public void preUpdate() {
        this.setUpdateTime(Instant.now().getEpochSecond());
    }
}
