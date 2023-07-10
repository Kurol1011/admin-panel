package kz.management.adminpanel.service.impl;

import jakarta.transaction.Transactional;
import kz.management.adminpanel.dto.RespondentDTO;
import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.exception.RespondentPostNotFound;
import kz.management.adminpanel.exception.UserNotFoundException;
import kz.management.adminpanel.model.Respondent;
import kz.management.adminpanel.model.User;
import kz.management.adminpanel.repo.RespondentRepository;
import kz.management.adminpanel.repo.UserRepository;
import kz.management.adminpanel.security.AuthUserDetails;
import kz.management.adminpanel.service.intf.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RespondentRepository respondentRepository;

    public UserServiceImpl(UserRepository userRepository, RespondentRepository respondentRepository) {
        this.userRepository = userRepository;
        this.respondentRepository = respondentRepository;
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        AuthUserDetails user = (AuthUserDetails) authentication.getPrincipal();

        return userRepository.findByEmail(user.getUsername()).orElseThrow(()->new UserNotFoundException("user not found by email!"));
    }


    @Override
    //@Transactional todo is necessary?
    public UserDTO convertToUserDTO(User user) {
        userRepository.findByEmail(user.getEmail()).orElseThrow(()->new UserNotFoundException("user not found by email!"));
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setEmail(user.getEmail());
        userDTO.setDateOfBirth(user.getDateOfBirth());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @Override
    public Respondent convertToRespondent(RespondentDTO respondentDTO) {
        Respondent respondent = new Respondent();
        respondent.setFullName(getCurrentUser().getFirstName() + " " + getCurrentUser().getLastName());
        respondent.setAmountComputers(respondentDTO.getAmountComputers());
        respondent.setAmountEmployee(respondentDTO.getAmountEmployee());
        respondent.setDate(respondentDTO.getDate());
        respondent.setUser(getCurrentUser());
        return respondent;
    }

    @Override
    public RespondentDTO convertToRespondentDTO(Respondent respondent) {
        respondentRepository.findById(respondent.getId()).orElseThrow(() -> new RespondentPostNotFound("respondent post not found by id!"));
        RespondentDTO respondentDTO = new RespondentDTO();
        respondentDTO.setId(respondent.getId());
        respondentDTO.setFullName(respondent.getFullName());
        respondentDTO.setAmountComputers(respondent.getAmountComputers());
        respondentDTO.setAmountEmployee(respondent.getAmountEmployee());
        respondentDTO.setDate(respondent.getDate());
        respondentDTO.setOwnerId(respondent.getUser().getId());
        return respondentDTO;
    }

    @Override
    public void createRespondentPost(RespondentDTO respondentDTO) {
        Respondent respondent = convertToRespondent(respondentDTO);
        respondentRepository.save(respondent);
    }

    @Override
    public List<RespondentDTO> getAllRespondents() {
        return respondentRepository.findAll().stream().map(r -> convertToRespondentDTO(r)).collect(Collectors.toList());
    }

    @Override
    public void removeRespondentPost(RespondentDTO respondentDTO) {
        respondentRepository.deleteById(respondentDTO.getId());
    }

    @Override
    public void updateRespondentPost(RespondentDTO respondentDTO) {
        Respondent respondent = respondentRepository.findById(respondentDTO.getId()).orElseThrow(()->new RespondentPostNotFound("respondent post not found!"));
        respondent.setAmountComputers(respondentDTO.getAmountComputers());
        respondent.setAmountEmployee(respondentDTO.getAmountEmployee());
        respondent.setDate(respondentDTO.getDate());
        respondentRepository.save(respondent);
    }

    @Override
    public User getOwnerRespondentPost(RespondentDTO respondentDTO) {
        User user = respondentRepository.findById(respondentDTO.getId()).orElseThrow(() -> new RespondentPostNotFound("respondent post not found!")).getUser();
        return user;
    }
}
