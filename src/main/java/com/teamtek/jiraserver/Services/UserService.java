package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.DataTransferObject.UserDto;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Utils.*;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String registerNewUser(UserRegisterBody userRegisterBody);
    boolean checkNullPass(String userId);
    String setPassword(SetPassUserBody setPassUserBody);

    String updatePassword(UpdatePassUserBody updatePassUserBody);
    UserDto getUserByEmail(String email);

    Users decodeGoogleToken(String token);


}
