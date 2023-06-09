package kz.management.adminpanel.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    @CrossOrigin(origins = "http://localhost:8080")
    public ResponseEntity<String> getHello(){
        return ResponseEntity.ok("Hello babe");
    }
}
