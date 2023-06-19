package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Users;

import com.teamtek.jiraserver.Utils.*;

import com.teamtek.jiraserver.Utils.UserRegisterBody;

import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface UserService {

    UserResponseBody loginGoogle(GoogleAuthToken googleAuthToken);

    UserResponseBody loginIdPass( UserLoginBody userLoginBody);
    String registerNewUser(UserRegisterBody userRegisterBody);
    boolean checkNullPass(String userId);

    String setPassword(SetPassUserBody setPassUserBody);

    String updatePassword(UpdatePassUserBody updatePassUserBody);


    Users getUserByEmail(String email);

    Users decodeGoogleToken(String token);


}
