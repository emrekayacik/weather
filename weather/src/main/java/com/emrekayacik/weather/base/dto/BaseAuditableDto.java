package com.emrekayacik.weather.base.dto;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Embeddable
@Getter
@Setter
public class BaseAuditableDto {
    private LocalDateTime createdDate;
    private Long createdUser;
    private LocalDateTime modifiedDate;
    private Long modifiedUser;
}
