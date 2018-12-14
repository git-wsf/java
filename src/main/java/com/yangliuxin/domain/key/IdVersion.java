package com.yangliuxin.domain.key;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class IdVersion implements Serializable {
    @Column(name="id")
    @Setter
    @Getter
    private Long id;

    @Column(name="version")
    @Setter
    @Getter
    private Integer version;
}
