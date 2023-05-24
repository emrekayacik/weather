package com.emrekayacik.weather.service.user;

import com.emrekayacik.weather.base.service.BaseEntityService;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserEntityService extends BaseEntityService<User, UserRepository> {
    UserRepository userRepository;
    public UserEntityService(UserRepository repository) {
        super(repository);
        this.userRepository = repository;
    }
    User findFirstByUsername(String username){
        return userRepository.findFirstByUsername(username);
    }
    void updateEmailByUsername(String username, String email){
        userRepository.updateEmailByUsername(username,email);
    }

}
