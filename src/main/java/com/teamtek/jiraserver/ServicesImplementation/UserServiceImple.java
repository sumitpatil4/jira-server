package com.teamtek.jiraserver.ServicesImplementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtek.jiraserver.Configuration.Security.JwtTokenUtils;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.UserService;

import com.teamtek.jiraserver.Utils.*;

import com.teamtek.jiraserver.Utils.UserRegisterBody;

import org.apache.catalina.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
public class UserServiceImple implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    JwtTokenUtils jwtTokenUtil;

    @Override
    public ResponseEntity<?> loginGoogle(GoogleAuthToken googleAuthToken) {
        try{
            Users user = decodeGoogleToken(googleAuthToken.getToken());
            String accessToken = jwtTokenUtil.generateAccessToken(user);

            UserResponseBody userResponseBody = new UserResponseBody(user.getId(),user.getFName(),user.getLName(), user.getEmail(), user.getProfileImg(), user.getRole(),accessToken);

            return new ResponseEntity<>(userResponseBody, HttpStatus.OK);
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    public ResponseEntity<?> loginIdPass(UserLoginBody userLoginBody) {

        try{
            String email = userLoginBody.getEmail();
            String pass = userLoginBody.getPassword();

            Users user= getUserByEmail(email);
            if(user==null){
                return null;
            }

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

    @Override
    public String registerNewUser(UserRegisterBody userRegisterBody) {
        String email=userRegisterBody.getEmail();
        String fname=userRegisterBody.getFname();
        String lname=userRegisterBody.getLname();
        String password=userRegisterBody.getPassword();
        if(userRepository.findByEmail(email)!=null){
            return "User already exist please login either by Google or use your password.";
        }
        if(email==null || fname==null || password==null){
            return "Please fill all the fields";
        }
        Users users=new Users();
        users.setId(UUID.randomUUID().toString());
        users.setFName(fname);
        users.setLName(lname);
        users.setEmail(email);
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pass=bCryptPasswordEncoder.encode(password);
        users.setPassword(pass);
        Users newUser=this.userRepository.save(users);

        return "Welcome to Accolite Jira. Please Login With your Credentials To Get Started.";
    }

    @Override
    public boolean checkNullPass(String userId) {
        Users users=this.userRepository.findById(userId).orElseThrow(null);

        if (users.getPassword()==null){
            return false;
        }
        return true;
    }




    @Override
    public String setPassword(SetPassUserBody setPassUserBody) {
        String userId = setPassUserBody.getUserId();
        String password = setPassUserBody.getPassword();
        Users user=this.userRepository.findById(userId).orElseThrow();
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pass=bCryptPasswordEncoder.encode(password);
        user.setPassword(pass);
        this.userRepository.save(user);
        return "You have successfully set your password.";
    }

    @Override
    public String updatePassword(UpdatePassUserBody updatePassUserBody) {
        String userId = updatePassUserBody.getUserId();
        String oldPassword = updatePassUserBody.getOldPassword();
        String newPassword = updatePassUserBody.getNewPassword();
        Users user=this.userRepository.findById(userId).orElseThrow();
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        boolean check =bCryptPasswordEncoder.matches(oldPassword,user.getPassword());
        String newPass=bCryptPasswordEncoder.encode(newPassword);
        if(check){
            user.setPassword(newPass);
            this.userRepository.save(user);
        }else {
            return "You have entered wrong password";
        }
        return "You have successfully changed your password.";
    }


    @Override
    public Users getUserByEmail(String email) {
        Users users=this.userRepository.findByEmail(email).orElse(null);

        return users;
    }

    @Override
    public Users decodeGoogleToken(String token) {
        String[] chunks = token.split("\\.");
        String payload = new String(Base64.decodeBase64(chunks[1]));
        Map<String, String> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(payload, new TypeReference<Map<String, String>>() {
            });
        } catch (Exception e) {
            e.printStackTrace();
        }



       Users users=this.userRepository.findByEmail(map.get("email")).orElse(null);


        if (users != null) {
            return users;
        }

        Users newuser = new Users();
        System.out.println("hoi");
        newuser.setId(UUID.randomUUID().toString());
        newuser.setEmail(map.get("email"));
        newuser.setFName(map.get("given_name"));
        newuser.setLName(map.get("family_name"));
        newuser.setProfileImg(map.get("picture"));
        newuser.setRole("USER");
        Users users1=this.userRepository.save(newuser);

        return users1;
    }



}
