package com.emrekayacik.weather.base.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * A base DTO class that provides audit fields for tracking entity creation and modification.
 * This class is intended to be embedded within other DTO classes to inherit the audit fields.
 */
@Embeddable
@Getter
@Setter
public class BaseAuditableDto {
    private LocalDateTime createdDate;
    private Long createdUser;
    private LocalDateTime modifiedDate;
    private Long modifiedUser;
}
