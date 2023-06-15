package com.teamtek.jiraserver.Controllers;


import com.teamtek.jiraserver.DataTransferObject.UserDto;
import com.teamtek.jiraserver.Services.UserService;
import com.teamtek.jiraserver.Utils.UserRegisterBody;
import com.teamtek.jiraserver.Utils.UserResponseBody;
import org.apache.catalina.User;
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
    public ResponseEntity<String> createUser(@RequestBody UserRegisterBody userRegisterBody){
        String message = this.userService.registerNewUser(userRegisterBody);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }

    @GetMapping("/userByEmail/{email}")
    public ResponseEntity<UserDto> getUserByEmail(@PathVariable String email){
        UserDto userDto=this.userService.getUserByEmail(email);
        return new ResponseEntity<>(userDto,HttpStatus.OK);
    }

    @GetMapping("/checkUserPass/{userId}")
    public ResponseEntity<Boolean> checkNullPassword(@PathVariable String userId){
        Boolean check=this.userService.checkNullPass(userId);
        return new ResponseEntity<>(check,HttpStatus.OK);
    }


    @PutMapping("userId/{userId}/updatePassword/{password}")
    public ResponseEntity<String> updatePassword(@PathVariable String userId,@PathVariable String password){
        String message=this.userService.updatePassword(userId,password);
        return ResponseEntity.ok(message);
    }


}
