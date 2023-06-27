package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.JiraRole;
import com.teamtek.jiraserver.Services.JiraRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/jiraRole")
public class JiraRoleController {

    @Autowired
    private JiraRoleService jiraRoleService;

    @PostMapping("/addRole")
    public ResponseEntity<String> addNewRole(@RequestParam String role){
        return jiraRoleService.addNewRole(role);
    }

    @GetMapping("/getAllRole")
    public ResponseEntity<List<JiraRole>> getAllRole(){
        return jiraRoleService.getAllRole();
    }
}
