package com.emrekayacik.weather.service.user;

import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.mapper.UserMapper;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.request.UserLoginRequest;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceContract {
    private final UserEntityService userEntityService;
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    private final BCryptPasswordEncoder passwordEncoder;


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
    public UserDto save(UserDto userDto){
        User userFound = userEntityService.findFirstByUsername(userDto.getUsername());

        if(userFound != null){
            throw new ItemNotFoundException(() ->
                    "Username is already taken by another user, please change your username."
            );
        }

        String hashedPassword = passwordEncoder.encode(userDto.getPassword());

        User userEntity = INSTANCE.convertToEntity(userDto);
        userEntity.setPassword(hashedPassword);
        User user = userEntityService.save(userEntity);

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

    //TODO: JWT
    public boolean login(UserLoginRequest request){

        String storedHashedPassword = userEntityService.findFirstByUsername(request.username()).getPassword();

        return passwordEncoder.matches(request.password(), storedHashedPassword);
    }

}
