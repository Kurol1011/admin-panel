package kz.management.adminpanel.service.impl;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.repo.UserRepository;
import kz.management.adminpanel.security.AuthUserDetails;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        return userRepository.findByEmail(user.getUsername()).orElseThrow(()->new RuntimeException("user not found!")); //TODO new ClientIsNotFoundException("Client not found!")
    }

    @Override
    public UserDTO convertToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setRole(user.getRole());
        return userDTO;
    }
}
