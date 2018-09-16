package com.test.demo.domain;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.io.Serializable;

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
