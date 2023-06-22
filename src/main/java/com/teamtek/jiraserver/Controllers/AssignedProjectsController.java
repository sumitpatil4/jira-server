package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;

import java.util.*;
@RestController
@RequestMapping("api/assignToProject")
public class AssignedProjectsController {

    @Autowired
    private AssignedProjectsService assignedProjectsService;

    @PostMapping("/team/{teamId}")
    public ResponseEntity<AssignedProjects> addUserToTeam(@PathVariable long teamId,@RequestBody AssignedProjectRequestBody assignedProjectRequestBody){
        return  assignedProjectsService.addUserToATeam(teamId,assignedProjectRequestBody);
    }
    @GetMapping("team/{teamId}")
    public ResponseEntity<List<AssignedProjects>> getAllUsersOfTeam(@PathVariable long teamId){
        return assignedProjectsService.getAllUsersOfATeam(teamId);

    }
    @GetMapping("user/{assignProjectId}")
    public ResponseEntity<AssignedProjects> getDetailOfAssigned(@PathVariable("assignProjectId") long id){
        return assignedProjectsService.getDetailofAssigned(id);
    }
    @PutMapping("removeUserFromTeam/{assignProjectId}")
    public ResponseEntity<String> removeUserFromTeam(@PathVariable("assignProjectId") long id){
        return assignedProjectsService.removeUserFromTeam(id);

    }
    @PutMapping("updateCapacity/{assignProjectId}/capacity/{capacity}")
    public ResponseEntity<String> updateCapacity(@PathVariable("assignProjectId") long id,@PathVariable int capacity){
        return assignedProjectsService.updateCapacityOfUser(id,capacity);

    }



}
