package kz.management.adminpanel.service.intf;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.security.RegisterRequest;

import java.util.List;

public interface AdminService {
    void createUser(RegisterRequest request);
    List<User> getAllUsers();


    void deleteAccount();
    void updateAccount();
}
