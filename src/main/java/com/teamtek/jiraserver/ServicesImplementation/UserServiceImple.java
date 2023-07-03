package com.teamtek.jiraserver.ServicesImplementation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtek.jiraserver.Configuration.Security.JwtTokenUtils;
import com.teamtek.jiraserver.Model.JiraRole;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.JiraRoleRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.UserService;

import com.teamtek.jiraserver.Utils.*;

import com.teamtek.jiraserver.Utils.UserRegisterBody;

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
    private JiraRoleRepository jiraRoleRepository;
    @Autowired
    JwtTokenUtils jwtTokenUtil;

    @Override
    public ResponseEntity<?> loginGoogle(GoogleAuthToken googleAuthToken) {
        try{
            Users user = decodeGoogleToken(googleAuthToken.getToken());
            String accessToken = jwtTokenUtil.generateAccessToken(user);

            UserResponseBody userResponseBody = new UserResponseBody(user.getId(),user.getFName(),user.getLName(), user.getEmail(), user.getProfileImg(), user.getJiraRole(),accessToken);

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

            Users user= getUserByEmail(email).getBody();
            if(user==null){
                return null;
            }

            String credential = user.getPassword();

            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            boolean check  = bCryptPasswordEncoder.matches(pass,credential);

            if(check){
                String accessToken = jwtTokenUtil.generateAccessToken(user);
                UserResponseBody userResponseBody = new UserResponseBody(user.getId(),user.getFName(),user.getLName(), user.getEmail(), user.getProfileImg(), user.getJiraRole(),accessToken);
                return new ResponseEntity<>(userResponseBody, HttpStatus.OK);
            }
            return null;
        }
        catch (BadCredentialsException ex){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



    @Override
    public ResponseEntity<Boolean> checkNullPass(String userId) {
        Users users=this.userRepository.findById(userId).orElseThrow(null);

        if (users.getPassword()==null){
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }




    @Override
    public ResponseEntity<String> setPassword(SetPassUserBody setPassUserBody) {
        String userId = setPassUserBody.getUserId();
        String password = setPassUserBody.getPassword();
        Users user=this.userRepository.findById(userId).orElseThrow();
        String message;
        if(user.getPassword()!=null){
            message= "Please Update Password";
            return new ResponseEntity<>(message,HttpStatus.OK);
        }
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pass=bCryptPasswordEncoder.encode(password);
        user.setPassword(pass);
        this.userRepository.save(user);
        message= "You have successfully set your password.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> updatePassword(UpdatePassUserBody updatePassUserBody) {
        String userId = updatePassUserBody.getUserId();
        String oldPassword = updatePassUserBody.getOldPassword();
        String newPassword = updatePassUserBody.getNewPassword();
        Users user=this.userRepository.findById(userId).orElseThrow();
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        boolean check =bCryptPasswordEncoder.matches(oldPassword,user.getPassword());
        String newPass=bCryptPasswordEncoder.encode(newPassword);
        String message;
        if(check){
            user.setPassword(newPass);
            this.userRepository.save(user);
        }else {
            message= "You have entered wrong password";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        message= "You have successfully changed your password.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Users> getUserByEmail(String email) {
        Users users=this.userRepository.findByEmail(email).orElse(null);

        return new ResponseEntity<>(users, HttpStatus.OK);
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
        newuser.setId(UUID.randomUUID().toString());
        newuser.setEmail(map.get("email"));
        newuser.setFName(map.get("given_name"));
        newuser.setLName(map.get("family_name"));
        newuser.setProfileImg(map.get("picture"));
        JiraRole jiraRole=this.jiraRoleRepository.findById(1).orElse(null);
        newuser.setJiraRole(jiraRole);
        Users users1=this.userRepository.save(newuser);

        return users1;
    }

    @Override
    public ResponseEntity<?> registerNewUser(UserRegisterBody userRegisterBody) {
        String email=userRegisterBody.getEmail();
        String fname=userRegisterBody.getFname();
        String lname=userRegisterBody.getLname();
        String password=userRegisterBody.getPassword();
        String message;

        Users users1=this.userRepository.findByEmail(email).orElse(null);
        if(users1!=null) {

            if (users1.getFName() == null && users1.getLName() == null && users1.getPassword() == null) {

                users1.setFName(fname);
                users1.setLName(lname);
                BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
                String pass=bCryptPasswordEncoder.encode(password);
                users1.setPassword(pass);

                this.userRepository.save(users1);

                UserLoginBody userLoginBody=new UserLoginBody();
                userLoginBody.setEmail(email);
                userLoginBody.setPassword(password);

                ResponseEntity<?> userResponseBody=this.loginIdPass(userLoginBody);

                return (ResponseEntity<UserResponseBody>) userResponseBody;
            }
            else {
                message = "User already exist please login either by Google or use your password.";
                return new ResponseEntity<>(message, HttpStatus.OK);
            }
        }

        if(email==null || fname==null || password==null){
            return null;
        }
        Users users=new Users();
        users.setId(UUID.randomUUID().toString());
        users.setFName(fname);
        users.setLName(lname);
        users.setEmail(email);JiraRole jiraRole=this.jiraRoleRepository.findById(1).orElse(null);
        users.setJiraRole(jiraRole);
        BCryptPasswordEncoder bCryptPasswordEncoder=new BCryptPasswordEncoder();
        String pass=bCryptPasswordEncoder.encode(password);
        users.setPassword(pass);
        Users newUser=this.userRepository.save(users);

        UserLoginBody userLoginBody=new UserLoginBody();
        userLoginBody.setEmail(email);
        userLoginBody.setPassword(password);

        ResponseEntity<?> userResponseBody=this.loginIdPass(userLoginBody);

        return (ResponseEntity<UserResponseBody>) userResponseBody;
    }




    @Override
    public ResponseEntity<String> updateJiraRole(String userId, Integer jiraRoleId) {
        try {
            Users users = this.userRepository.findById(userId).orElseThrow();
            JiraRole jiraRole = this.jiraRoleRepository.findById(jiraRoleId).orElseThrow();
            users.setJiraRole(jiraRole);
            this.userRepository.save(users);
            String message = "Role Updated Successfully";
            return new ResponseEntity<>(message,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }



    }

    public void addUserToTeam(String email){
        Users users = new Users();
        users.setId(UUID.randomUUID().toString());
        users.setEmail(email);
        this.userRepository.save(users);
    }


}
