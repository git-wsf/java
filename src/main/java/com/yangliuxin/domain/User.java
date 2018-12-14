package com.yangliuxin.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@Data
@EqualsAndHashCode(callSuper =false)
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
@DynamicInsert
@DynamicUpdate
public class User extends AbstractDomain {



    @Column(name="username")
    private String username;

    @Column(name = "userpwd")
    private String userpwd;

}
