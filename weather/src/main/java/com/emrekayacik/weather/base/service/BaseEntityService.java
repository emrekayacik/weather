package com.emrekayacik.weather.base.service;

import com.emrekayacik.weather.base.entity.BaseAuditableEntity;
import com.emrekayacik.weather.base.entity.BaseEntity;
import com.emrekayacik.weather.base.exception.GenericErrorMessage;
import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.base.exception.enums.UserErrorMessage;
import com.emrekayacik.weather.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

/**
 * A base service class for managing entities that extend BaseEntity.
 *
 * @param <E> the type of the entity
 * @param <R> the type of the repository for the entity
 */
@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    private final R repository;

    /**
     * Saves the entity, performing necessary auditing operations.
     *
     * @param entity the entity to be saved
     * @return the saved entity
     */
    @Transactional
    public E save(E entity){
        checkAuthentication(entity);
        entity = repository.save(entity);

        return entity;
    }

    private void checkAuthentication(E entity) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            User userDetails = (User) authentication.getPrincipal();
            setBaseAuditableEntity(entity, userDetails);
        }
    }

    /**
     * Retrieves all entities.
     *
     * @return a list of all entities
     */
    public List<E> findAll() {
        return repository.findAll();
    }

    /**
     * Deletes an entity by its ID.
     *
     * @param id the ID of the entity to be deleted
     */
    @Transactional
    public void delete(Long id){
        repository.deleteById(id);
    }

    /**
     * Deletes an entity.
     *
     * @param entity the entity to be deleted
     */
    @Transactional
    public void delete(E entity){
        checkAuthentication(entity);
        repository.delete(entity);
    }

    /**
     * Finds an entity by its ID.
     *
     * @param id the ID of the entity to be found
     * @return the found entity
     * @throws ItemNotFoundException if the entity is not found
     */
    public E findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(
                        GenericErrorMessage.builder().message(
                                UserErrorMessage.USER_NOT_FOUND.getMessage()
                        ).build()
                )
        );
    }

    private void setBaseAuditableEntity(E entity, User userDetails) {
        BaseAuditableEntity baseAuditableEntity = entity.getBaseAuditableEntity();
        if (baseAuditableEntity == null){
            baseAuditableEntity = new BaseAuditableEntity();
        }

        if (entity.getId()  == null){
            baseAuditableEntity.setCreatedDate(LocalDateTime.now());
            baseAuditableEntity.setCreatedUser(userDetails.getId());
        }
        else{
            baseAuditableEntity.setModifiedDate(LocalDateTime.now());
            baseAuditableEntity.setModifiedUser(userDetails.getId());
        }

        entity.setBaseAuditableEntity(baseAuditableEntity);
    }

}
