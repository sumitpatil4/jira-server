package com.teamtek.jiraserver.Controllers;


import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Services.UserService;
import com.teamtek.jiraserver.Utils.SetPassUserBody;
import com.teamtek.jiraserver.Utils.UpdatePassUserBody;
import com.teamtek.jiraserver.Utils.UserRegisterBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> createUser(@RequestBody UserRegisterBody userRegisterBody){
        return userService.registerNewUser(userRegisterBody);
    }

    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<Users> getUserByEmail(@PathVariable String email){
        return userService.getUserByEmail(email);
    }

    @GetMapping("/checkUserPass/{userId}")
    public ResponseEntity<Boolean> checkNullPassword(@PathVariable String userId){
        return userService.checkNullPass(userId);

    }


    @PutMapping("/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassUserBody updatePassUserBody){
        return userService.updatePassword(updatePassUserBody);
    }


    @PutMapping("/setPassword")
    public ResponseEntity<String> setPassword(@RequestBody SetPassUserBody setPassUserBody){
        return userService.setPassword(setPassUserBody);
    }
    @PutMapping("/updateUserRole/{userId}/roleId/{roleId}")
    public ResponseEntity<String> updateUserRole(@PathVariable String userId,@PathVariable Integer roleId){
        return userService.updateJiraRole(userId,roleId);
    }

}
