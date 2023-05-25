package kz.management.adminpanel.service.impl;

import kz.management.adminpanel.model.User;
import kz.management.adminpanel.model.enums.Role;
import kz.management.adminpanel.repo.UserRepository;
import kz.management.adminpanel.security.RegisterRequest;
import kz.management.adminpanel.service.intf.AdminService;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void createUser(RegisterRequest request) {
        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
