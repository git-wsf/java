package com.yangliuxin.domain;

import com.yangliuxin.domain.key.IdVersion;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "users_version")
@Data
@NoArgsConstructor
public class UserVersion {
    @Column(name="username")
    private String username;

    @Column(name = "userpwd")
    private String userpwd;

    @EmbeddedId
    private IdVersion pk = null;

    @Column(name = "create_time", nullable = false)
    private Long createTime;

    @Column(name = "update_time", nullable = false)
    private Long updateTime;

    public UserVersion(User user) {
        username = user.getUsername();
        userpwd = user.getUserpwd();
        createTime = user.getCreateTime() == null ? 0L : user.getCreateTime();
        updateTime = user.getUpdateTime() == null ? 0L : user.getUpdateTime();
        pk = new IdVersion();
        pk.setId(user.getId());
        pk.setVersion(user.getVersion());
    }

}
