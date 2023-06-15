package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Utils.UserRegisterBody;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    String registerNewUser(UserRegisterBody userRegisterBody);
    boolean checkNullPass(String userId);
    String updatePassword(String userId,String password);
    Users getUserByEmail(String email);

    Users decodeGoogleToken(String token);


}
