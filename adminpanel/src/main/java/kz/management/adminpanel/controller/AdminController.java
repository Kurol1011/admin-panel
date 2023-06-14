package kz.management.adminpanel.controller;

import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.security.RegisterRequest;
import kz.management.adminpanel.service.intf.AdminService;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class AdminController {

    private final AdminService adminService;

    private final UserService userService;

    public AdminController(AdminService adminService, UserService userService) {
        this.adminService = adminService;
        this.userService = userService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request){
        adminService.createUser(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<UserDTO> getAllUsers(){
        return adminService.getAllUsers().stream().map(k -> userService.convertToUserDTO(k)).collect(Collectors.toList());
    }



}
