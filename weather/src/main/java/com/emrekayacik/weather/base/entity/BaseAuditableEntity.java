package com.emrekayacik.weather.base.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAuditableEntity {
    @Column(name = "CREATED_DATE", updatable = false)
    private LocalDateTime createdDate;
    @Column(name = "ID_CREATED_USER", updatable = false)
    private Long createdUser;
    @Column(name = "MODIFIED_DATE", insertable = false)
    private LocalDateTime modifiedDate;
    @Column(name = "ID_MODIFIED_USER",insertable = false)
    private Long modifiedUser;
}
