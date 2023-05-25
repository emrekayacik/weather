package com.emrekayacik.weather.service.user;

import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.mapper.UserMapper;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceContract {
    private final UserEntityService userEntityService;
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    public List<UserDto> findAll(){

        List<User> userList = userEntityService.findAll();

        return INSTANCE.convertToDtoList(userList);
    }
    public UserDto findById(Long id){

        User user = userEntityService.findById(id);

        return INSTANCE.convertToDto(user);
    }
    public UserDto findByUsername(String username){

        User user = userEntityService.findFirstByUsername(username);
        if(user == null){
            throw new ItemNotFoundException(() ->
                    "User cannot found with username: " + username
            );
        }
        return INSTANCE.convertToDto(user);
    }
    public void deleteUserByUsername(UserDeleteRequest userDeleteRequest){
        UserDto userByUsername = findByUsername(userDeleteRequest.username());

        User user = INSTANCE.convertToEntity(userByUsername);

        userEntityService.delete(user);
    }
    public void changeEmail(UserChangeEmailRequest request){
        findByUsername(request.username());
        userEntityService.updateEmailByUsername(request.username(), request.email());
    }

    public UserDto save(UserDto userDto){
        User savedUser = userEntityService.save(INSTANCE.convertToEntity(userDto));
        return INSTANCE.convertToDto(savedUser);
    }
}
