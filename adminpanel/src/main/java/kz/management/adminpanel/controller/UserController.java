package kz.management.adminpanel.controller;

import kz.management.adminpanel.dto.RespondentDTO;
import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> showUserInfo(){
        return ResponseEntity.ok(userService.convertToUserDTO(userService.getCurrentUser()));
    }

    @GetMapping("/respondents")
    public ResponseEntity<List<RespondentDTO>> getAllRespondents(){
        return ResponseEntity.ok(userService.getAllRespondents());
    }

}
