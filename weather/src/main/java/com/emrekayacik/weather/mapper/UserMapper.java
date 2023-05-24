package com.emrekayacik.weather.mapper;

import com.emrekayacik.weather.base.mapper.BaseMapper;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserMapper extends BaseMapper<User, UserDto> {
}