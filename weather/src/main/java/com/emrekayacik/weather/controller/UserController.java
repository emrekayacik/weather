package com.emrekayacik.weather.controller;

import com.emrekayacik.weather.base.response.RestResponse;
import com.emrekayacik.weather.dto.UserDto;
import com.emrekayacik.weather.request.UserChangeEmailRequest;
import com.emrekayacik.weather.request.UserDeleteRequest;
import com.emrekayacik.weather.request.UserUpdateRequest;
import com.emrekayacik.weather.service.user.UserServiceContract;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller class for managing user-related endpoints.
 */
@RestController
@RequestMapping("users")
@Validated
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@CrossOrigin(origins="*")
public class UserController {
    private final UserServiceContract userServiceContract;

    /**
     * Retrieves all users.
     *
     * @return ResponseEntity containing a list of UserDto objects.
     */
    @GetMapping("/")
    @Operation(summary = "Get All Users")
    public ResponseEntity<RestResponse<List<UserDto>>> get() {
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findAll()));
    }

    /**
     * Retrieves a user by their ID.
     *
     * @param id The ID of the user.
     * @return ResponseEntity containing the UserDto object.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get User By Id")
    public ResponseEntity<RestResponse<UserDto>> findById(@PathVariable Long id) {
        return ResponseEntity.ok(RestResponse.of(userServiceContract.findById(id)));
    }

    /**
     * Deletes a user by their username.
     *
     * @param request The UserDeleteRequest containing the username.
     * @return ResponseEntity with no content.
     */
    @DeleteMapping("/")
    @Operation(summary = "Delete User By Username")
    public ResponseEntity<RestResponse<Object>> delete(@RequestBody @Valid UserDeleteRequest request) {
        userServiceContract.deleteUserByUsername(request);
        return ResponseEntity.ok(RestResponse.empty());
    }
    /**
     *
     * Updates the user by their id.
     *
     * @param request The UserUpdateRequest containing the username,password,name,surname,email,phone.
     * @return ResponseEntity with RestResponse of UserDto.
     */
    @PutMapping("/{id}/update")
    @Operation(summary = "Update User")
    public ResponseEntity<RestResponse<UserDto>> update(@PathVariable Long id,@Valid @RequestBody UserUpdateRequest request) {
        return ResponseEntity.ok(RestResponse.of(userServiceContract.update(id,request)));
    }

    /**
     * Updates the email of a user by their username.
     *
     * @param request The UserChangeEmailRequest containing the new email and username.
     * @return ResponseEntity with no content.
     */
    @PatchMapping("/")
    @Operation(summary = "Update Email By Username")
    public ResponseEntity<RestResponse<UserDto>> updateEmail(@Valid @RequestBody UserChangeEmailRequest request) {
        userServiceContract.changeEmail(request);
        return ResponseEntity.ok(RestResponse.empty());
    }
}
