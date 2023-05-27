package com.emrekayacik.weather.base.entity;

import jakarta.persistence.Embedded;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * An abstract base entity class that serves as the parent for all entities in the application.
 * It includes an embedded `BaseAuditableEntity` for tracking audit information.
 */
@Getter
@Setter
@MappedSuperclass
@Table
public abstract class BaseEntity implements Serializable, Cloneable, BaseEntityModel {
    @Embedded
    private BaseAuditableEntity baseAuditableEntity;
}
