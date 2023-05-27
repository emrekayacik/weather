package com.emrekayacik.weather.base.entity;

/**
 * An interface representing the base model for all entities in the application.
 * It defines the contract for retrieving the ID of an entity.
 */
public interface BaseEntityModel {

    /**
     * Retrieves the unique identifier of the entity.
     *
     * @return The ID of the entity.
     */
    Long getId();
}
