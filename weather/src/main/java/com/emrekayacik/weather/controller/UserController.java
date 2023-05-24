package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.request.UserLoginRequest;
import com.emrekayacik.weather.service.user.UserServiceContract;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
@Validated
@RequiredArgsConstructor
public class UserController{
    private final UserServiceContract userServiceContract;

    @GetMapping("/")
    public ResponseEntity<RestResponse<List<UserDto>>> get(){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findAll()));
    }
    @GetMapping("/{id}")
    public ResponseEntity<RestResponse<UserDto>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findById(id)));
    }
    @PostMapping("/")
    public ResponseEntity<RestResponse<UserDto>> save(@RequestBody @Valid UserDto userDto){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.save(userDto)));
    }
    @DeleteMapping("/")
    public ResponseEntity<RestResponse<Object>> delete(@RequestBody @Valid UserDeleteRequest request){
        userServiceContract.deleteUserByUsername(request);
        return ResponseEntity.ok(RestResponse.empty());
    }
    @PutMapping("/")
    public ResponseEntity<RestResponse<UserDto>> update(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.save(userDto)));
    }
    @PatchMapping("/")
    public ResponseEntity<RestResponse<UserDto>> updateEmail(@Valid @RequestBody UserChangeEmailRequest request){
        userServiceContract.changeEmail(request);
        return ResponseEntity.ok(RestResponse.empty());
    }
    @PostMapping("/login")
    public ResponseEntity<RestResponse<Boolean>> login(@RequestBody @Valid UserLoginRequest request){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.login(request)));
    }
}
