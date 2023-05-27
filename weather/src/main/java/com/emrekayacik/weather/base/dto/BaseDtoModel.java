package com.emrekayacik.weather.base.dto;

import java.io.Serializable;

/**
 * An interface that represents a base DTO model.
 * DTO models implementing this interface should provide a method to retrieve the unique identifier of the entity.
 */
public interface BaseDtoModel extends Serializable, Cloneable {

    /**
     * Retrieves the unique identifier of the entity.
     *
     * @return The ID of the entity.
     */
    Long getId();
}
