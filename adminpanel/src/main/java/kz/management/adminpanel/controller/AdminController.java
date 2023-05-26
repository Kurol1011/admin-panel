package kz.management.adminpanel.controller;

import kz.management.adminpanel.model.User;
import kz.management.adminpanel.security.RegisterRequest;
import kz.management.adminpanel.service.intf.AdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/create-user")
    public ResponseEntity<?> createUser(@RequestBody RegisterRequest request){
        adminService.createUser(request);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/users")
    public List<User> getAllUsers(){
        return adminService.getAllUsers();
    }



}
