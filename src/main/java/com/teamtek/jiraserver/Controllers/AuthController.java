package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Configuration.Security.JwtTokenUtils;
import com.teamtek.jiraserver.DataTransferObject.UserDto;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Services.UserService;
import com.teamtek.jiraserver.Utils.GoogleAuthToken;
import com.teamtek.jiraserver.Utils.UserLoginBody;
import com.teamtek.jiraserver.Utils.UserResponseBody;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Null;
import java.io.IOException;

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
    public ResponseEntity<UserResponseBody> login(@RequestBody GoogleAuthToken googleAuthToken){
        try{
            Users user = this.userService.decodeGoogleToken(googleAuthToken.getToken());
            String accessToken = jwtTokenUtil.generateAccessToken(user);

            UserResponseBody userResponseBody = new UserResponseBody(user.getId(),user.getFName(),user.getLName(), user.getEmail(), user.getProfileImg(), user.getRole(),accessToken);

            return new ResponseEntity<>(userResponseBody, HttpStatus.OK);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @PostMapping("/login/userByIdPass")
    public ResponseEntity<UserResponseBody> login(@RequestBody UserLoginBody userLoginBody){
        try{
            String email = userLoginBody.getEmail();
            String pass = userLoginBody.getPassword();
            UserDto userDto = this.userService.getUserByEmail(email);
            Users user = this.modelMapper.map(userDto,Users.class);
            String credential = user.getPassword();

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean check  = bCryptPasswordEncoder.matches(pass,credential);

            if(check){
                String accessToken = jwtTokenUtil.generateAccessToken(user);
                UserResponseBody userResponseBody = new UserResponseBody(user.getId(),user.getFName(),user.getLName(), user.getEmail(), user.getProfileImg(), user.getRole(),accessToken);
                return new ResponseEntity<>(userResponseBody, HttpStatus.OK);
            }
            return null;
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
