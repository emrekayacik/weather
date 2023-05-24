package com.emrekayacik.weather.base.service;

import com.emrekayacik.weather.base.entity.BaseAuditableEntity;
import com.emrekayacik.weather.base.entity.BaseEntity;
import com.emrekayacik.weather.base.exception.GenericErrorMessage;
import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.base.exception.enums.UserErrorMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public abstract class BaseEntityService<E extends BaseEntity, R extends JpaRepository<E, Long>> {

    private final R repository;
    public E save(E entity){
        BaseAuditableEntity baseAuditableEntity = entity.getBaseAuditableEntity();
        if (baseAuditableEntity == null){
            baseAuditableEntity = new BaseAuditableEntity();
        }

        if (entity.getId()  == null){
            baseAuditableEntity.setCreatedDate(LocalDateTime.now());
            //baseAdditionalFields.setCreatedBy(0L); // TODO: JWT
        }
        else{
            baseAuditableEntity.setModifiedDate(LocalDateTime.now());
            //baseAdditionalFields.setUpdatedBy(0L);// TODO: JWT
        }

        entity.setBaseAuditableEntity(baseAuditableEntity);
        entity = repository.save(entity);

        return entity;
    }

    public List<E> findAll() {
        return repository.findAll();
    }

    public void delete(Long id){
        repository.deleteById(id);
    }

    public void delete(E entity){
        repository.delete(entity);
    }

    public E findById(Long id){
        return repository.findById(id).orElseThrow(
                () -> new ItemNotFoundException(
                        GenericErrorMessage.builder().message(
                                UserErrorMessage.USER_NOT_FOUND.getMessage()
                        ).build()
                )
        );
    }

}
