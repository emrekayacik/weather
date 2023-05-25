package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.service.user.UserServiceContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@SecurityRequirement(name = "bearerAuth")
public class UserController{
    private final UserServiceContract userServiceContract;
    @GetMapping("/")
    @Operation(summary = "Get All Users")
    public ResponseEntity<RestResponse<List<UserDto>>> get(){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findAll()));
    }
    @GetMapping("/{id}")
    @Operation(summary = "Get User By Id")
    public ResponseEntity<RestResponse<UserDto>> findById(@PathVariable Long id){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findById(id)));
    }
    @DeleteMapping("/")
    @Operation(summary = "Delete User By Username")
    public ResponseEntity<RestResponse<Object>> delete(@RequestBody @Valid UserDeleteRequest request){
        userServiceContract.deleteUserByUsername(request);
        return ResponseEntity.ok(RestResponse.empty());
    }
    @PutMapping("/")
    @Operation(summary = "Update User")
    public ResponseEntity<RestResponse<UserDto>> update(@Valid @RequestBody UserDto userDto){
        return ResponseEntity.ok(RestResponse.of(userServiceContract.save(userDto)));
    }
    @PatchMapping("/")
    @Operation(summary = "Update Email By Username")
    public ResponseEntity<RestResponse<UserDto>> updateEmail(@Valid @RequestBody UserChangeEmailRequest request){
        userServiceContract.changeEmail(request);
        return ResponseEntity.ok(RestResponse.empty());
    }

}
