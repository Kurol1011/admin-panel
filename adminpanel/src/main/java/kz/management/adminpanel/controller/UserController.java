package kz.management.adminpanel.controller;

import kz.management.adminpanel.dto.RespondentDTO;
import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping("/create-respondent-post")
    public ResponseEntity<?> createRespondentPost(@RequestBody RespondentDTO respondentDTO){
        userService.createRespondentPost(respondentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/remove-respondent-post")
    public ResponseEntity<?> removeRespondentPost(@RequestBody RespondentDTO respondentDTO){
        if(userService.getCurrentUser().getId() != userService.getOwnerRespondentPost(respondentDTO).getId()) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        userService.removeRespondentPost(respondentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/update-respondent-post")
    public ResponseEntity<?> updateRespondentPost(@RequestBody RespondentDTO respondentDTO){
        if(userService.getCurrentUser().getId() != userService.getOwnerRespondentPost(respondentDTO).getId()) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
        userService.updateRespondentPost(respondentDTO);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
