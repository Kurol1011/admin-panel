package kz.management.adminpanel.service.impl;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.exception.UserIsAlreadyExistsException;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.repo.UserRepository;
import kz.management.adminpanel.security.RegisterRequest;
import kz.management.adminpanel.service.intf.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(RegisterRequest request) {

        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserIsAlreadyExistsException("User with this email is already exists");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDateOfBirth(request.getDateOfBirth());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    @Override
    public void deleteAccount() {

    }

    @Override
    public void updateAccount() {

    }
}
