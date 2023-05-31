package com.emrekayacik.weather.base.dto;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import lombok.*;

/**
 * An abstract base DTO class that provides common fields and functionality for other DTO classes.
 * This class is intended to be extended by other DTO classes to inherit the common fields and methods.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class BaseDto implements BaseDtoModel {
    @Embedded
    private BaseAuditableDto auditable;

    protected Long id;
    @Override
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
}
