package kz.management.adminpanel.service.impl;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.exception.UserIsAlreadyExistsException;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.model.enums.Role;
import kz.management.adminpanel.repo.UserRepository;
import kz.management.adminpanel.security.RegisterRequest;
import kz.management.adminpanel.service.impl.AdminServiceImpl;
import kz.management.adminpanel.service.intf.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AdminServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AdminServiceImpl adminService;

    private RegisterRequest request;

    @BeforeEach
    void setup() {
        request = new RegisterRequest();
        request.setFirstName("Zack");
        request.setLastName("Mack");
        request.setDateOfBirth(new Date());
        request.setEmail("zack@gmail.com");
        request.setPassword("password");
        request.setRole(Role.USER);
    }

    @Test
    void createUser_ValidRequest_SuccessfullyCreated() {

        Mockito.when(userRepository.existsByEmail(request.getEmail())).thenReturn(false);
        Mockito.when(passwordEncoder.encode(request.getPassword())).thenReturn("encodedPassword");

        assertDoesNotThrow(() -> adminService.createUser(request));

        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void createUser_UserExists_ThrowsException() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("zack@gmail.com");

        when(userRepository.existsByEmail(request.getEmail())).thenReturn(true);

        assertThrows(UserIsAlreadyExistsException.class, () -> adminService.createUser(request));
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void getAllUsers_NoUsers_ReturnsEmptyList() {
        List<User> userList = new ArrayList<>();
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = adminService.getAllUsers();

        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    void getAllUsers_UsersExist_ReturnsUserList() {
        List<User> userList = new ArrayList<>();
        userList.add(new User());
        userList.add(new User());
        when(userRepository.findAll()).thenReturn(userList);

        List<User> result = adminService.getAllUsers();

        assertNotNull(result);
        assertEquals(userList.size(), result.size());
    }
}

