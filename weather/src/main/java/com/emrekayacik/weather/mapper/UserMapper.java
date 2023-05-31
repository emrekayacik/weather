package com.emrekayacik.weather.mapper;

import com.emrekayacik.weather.base.mapper.AuditableMapper;
import com.emrekayacik.weather.base.mapper.BaseMapper;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.request.UserUpdateRequest;
import org.mapstruct.Mapper;

/**
 * Mapper interface for mapping User entities and DTOs.
 */
@Mapper(uses = {AuditableMapper.class})
public interface UserMapper extends BaseMapper<User, UserDto> {

    /**
     * Converts a UserUpdateRequest object to a UserDto object.
     *
     * @param request the UserUpdateRequest object
     * @return the converted UserDto object
     */
    UserDto convertUpdateRequestToDto(UserUpdateRequest request);
}
