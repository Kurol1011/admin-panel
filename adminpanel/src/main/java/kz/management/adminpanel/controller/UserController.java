package kz.management.adminpanel.controller;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public UserDTO showUserInfo(){
        return userService.convertToUserDTO(userService.getCurrentUser());
    }

}
