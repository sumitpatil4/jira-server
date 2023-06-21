package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Configuration.Security.JwtTokenUtils;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Services.UserService;
import com.teamtek.jiraserver.Utils.GoogleAuthToken;
import com.teamtek.jiraserver.Utils.UserLoginBody;
import com.teamtek.jiraserver.Utils.UserResponseBody;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private UserService userService;
    @Autowired
    JwtTokenUtils jwtTokenUtil;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/login/userByGoogle")
    public ResponseEntity<?> login(@RequestBody GoogleAuthToken googleAuthToken){
        return userService.loginGoogle(googleAuthToken);
    }


    @PostMapping("/login/userByIdPass")
    public ResponseEntity<?> login(@RequestBody UserLoginBody userLoginBody) {
        return userService.loginIdPass(userLoginBody);
    }
}
