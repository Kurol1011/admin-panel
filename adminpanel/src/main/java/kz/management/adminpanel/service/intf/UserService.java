package kz.management.adminpanel.service.intf;

import kz.management.adminpanel.dto.RespondentDTO;
import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.model.Respondent;
import kz.management.adminpanel.model.User;

import java.util.List;

public interface UserService {
    User getCurrentUser();

    UserDTO convertToUserDTO(User user);

    Respondent convertToRespondent(RespondentDTO respondentDTO);

    RespondentDTO convertToRespondentDTO(Respondent respondent);

    void createRespondentPost(RespondentDTO respondentDTO);

    List<RespondentDTO> getAllRespondents();
}
