package com.emrekayacik.weather.base.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class BaseDto implements BaseDtoModel {
    @Embedded
    private BaseAuditableDto auditableDto;

    protected Long id;
    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
