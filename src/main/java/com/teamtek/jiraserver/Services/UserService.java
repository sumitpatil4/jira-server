package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Users;

import com.teamtek.jiraserver.Utils.*;

import com.teamtek.jiraserver.Utils.UserRegisterBody;

import org.apache.catalina.User;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public interface UserService {

    public ResponseEntity<?> loginGoogle(GoogleAuthToken googleAuthToken);
    public ResponseEntity<?> loginIdPass( UserLoginBody userLoginBody);
    public ResponseEntity<Boolean> checkNullPass(String userId);
    public ResponseEntity<String> setPassword(SetPassUserBody setPassUserBody);
    public ResponseEntity<String> updatePassword(UpdatePassUserBody updatePassUserBody);
    public ResponseEntity<Users> getUserByEmail(String email);
    Users decodeGoogleToken(String token);
    ResponseEntity<?> registerNewUser(UserRegisterBody userRegisterBody);
    ResponseEntity<String> updateJiraRole(String userId, Integer jiraRoleId);
}
