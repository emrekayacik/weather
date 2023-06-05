package com.emrekayacik.weather.service;

import com.emrekayacik.weather.base.exception.custom.ItemNotFoundException;
import com.emrekayacik.weather.entity.User;
import com.emrekayacik.weather.repository.UserRepository;
import com.emrekayacik.weather.service.user.UserEntityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserEntityServiceTest {

    private UserEntityService userEntityService;

    @Mock
    private UserRepository userRepositoryMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        userEntityService = new UserEntityService(userRepositoryMock);
    }

    @Test
    void should_save_authenticatedUser_when_entitySavedWithAuditableInformation() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userRepositoryMock.save(user)).thenReturn(user);

        // Act
        User savedUser = userEntityService.save(user);

        // Assert
        assertNotNull(savedUser.getAuditable());
        assertNull(savedUser.getAuditable().getCreatedUser());
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    void should_save_anonymousUser_when_entitySavedWithoutAuditableInformation() {
        // Arrange
        User user = new User();
        user.setUsername("testUser");
        Authentication authentication = new AnonymousAuthenticationToken("anonymousUser", "anonymousUser",
                List.of(() -> "ROLE_ANONYMOUS"));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        when(userRepositoryMock.save(user)).thenReturn(user);

        // Act
        User savedUser = userEntityService.save(user);

        // Assert
        assertNull(savedUser.getAuditable());
        verify(userRepositoryMock, times(1)).save(user);
    }

    @Test
    void should_findAll_returnListOfUsers_when_validCall() {
        // Arrange
        List<User> expectedUsers = Arrays.asList(new User(), new User());
        when(userRepositoryMock.findAll()).thenReturn(expectedUsers);

        // Act
        List<User> actualUsers = userEntityService.findAll();

        // Assert
        assertEquals(expectedUsers.size(), actualUsers.size());
        assertTrue(actualUsers.containsAll(expectedUsers));
        verify(userRepositoryMock, times(1)).findAll();
    }

    @Test
    void should_deleteById_when_existingId() {
        // Arrange
        Long userId = 1L;

        // Act
        userEntityService.delete(userId);

        // Assert
        verify(userRepositoryMock, times(1)).deleteById(userId);
    }

    @Test
    void should_delete_when_existingEntity() {
        // Arrange
        User user = new User();

        // Act
        userEntityService.delete(user);

        // Assert
        verify(userRepositoryMock, times(1)).delete(user);
    }

    @Test
    void should_findById_when_existingId() {
        // Arrange
        Long userId = 1L;
        User expectedUser = new User();
        when(userRepositoryMock.findById(userId)).thenReturn(Optional.of(expectedUser));

        // Act
        User actualUser = userEntityService.findById(userId);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepositoryMock, times(1)).findById(userId);
    }

    @Test
    void should_findById_throwItemNotFoundException_when_nonExistingId() {
        // Arrange
        Long nonExistingId = 999L;
        when(userRepositoryMock.findById(nonExistingId)).thenReturn(Optional.empty());

        // Act and Assert
        assertThrows(ItemNotFoundException.class, () -> userEntityService.findById(nonExistingId));
        verify(userRepositoryMock, times(1)).findById(nonExistingId);
    }

    @Test
    void should_findFirstByUsername_when_existingUsername_() {
        // Arrange
        String username = "testUser";
        User expectedUser = new User();
        when(userRepositoryMock.findFirstByUsername(username)).thenReturn(expectedUser);

        // Act
        User actualUser = userEntityService.findFirstByUsername(username);

        // Assert
        assertEquals(expectedUser, actualUser);
        verify(userRepositoryMock, times(1)).findFirstByUsername(username);
    }

    @Test
    void should_updateEmailByUsername_when_validData() {
        // Arrange
        String username = "testUser";
        String newEmail = "dummy@dummy.com";

        // Act
        userEntityService.updateEmailByUsername(username, newEmail);

        // Assert
        verify(userRepositoryMock, times(1)).updateEmailByUsername(eq(username), eq(newEmail), any());
    }

    @Test
    void should_findFirstByUsername_returnNull_when_nonExistingUsername() {
        // Arrange
        String nonExistingUsername = "nonExistingUser";
        when(userRepositoryMock.findFirstByUsername(nonExistingUsername)).thenReturn(null);

        // Act
        User actualUser = userEntityService.findFirstByUsername(nonExistingUsername);

        // Assert
        assertNull(actualUser);
        verify(userRepositoryMock, times(1)).findFirstByUsername(nonExistingUsername);
    }

    @Test
    void should_entityNotDelete_when_nonExistingId() {
        // Arrange
        Long nonExistingId = 999L;
        doThrow(ItemNotFoundException.class).when(userRepositoryMock).deleteById(nonExistingId);

        // Act and Assert
        assertThrows(ItemNotFoundException.class, () -> userEntityService.delete(nonExistingId));
        verify(userRepositoryMock, times(1)).deleteById(nonExistingId);
    }

    @Test
    void should_findById_throwIllegalArgumentException_when_nullId() {
        // Act and Assert
        assertThrows(ItemNotFoundException.class, () -> userEntityService.findById(null));
    }

}
