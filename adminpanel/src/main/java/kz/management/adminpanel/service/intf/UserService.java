package kz.management.adminpanel.service.intf;

import kz.management.adminpanel.dto.RespondentDTO;
import kz.management.adminpanel.dto.UserDTO;
import kz.management.adminpanel.model.Respondent;
import kz.management.adminpanel.model.User;

public interface UserService {
    User getCurrentUser();
    UserDTO convertToUserDTO(User user);

    Respondent convertToRespondent(RespondentDTO respondentDTO);

    void createFormInfoComputers(RespondentDTO respondentDTO);
}
