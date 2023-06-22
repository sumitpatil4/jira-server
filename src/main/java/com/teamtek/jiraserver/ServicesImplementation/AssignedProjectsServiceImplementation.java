package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Repository.TeamRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedProjectsServiceImplementation implements AssignedProjectsService  {

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;
    @Autowired
    private TeamRepository teamRepository;


    @Override
    public ResponseEntity<List<AssignedProjects>> getAllUsersOfATeam(long id) {
        Teams teams=this.teamRepository.findById(id).orElseThrow();
        List<AssignedProjects> assignedProjects=this.assignedProjectsRepository.getAllAssignedByTeam(teams);
        return new ResponseEntity<>(assignedProjects, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<AssignedProjects> getDetailofAssigned(long id) {
        AssignedProjects assignedProjects=this.assignedProjectsRepository.findById(id).orElse(null);
            return new ResponseEntity<>(assignedProjects, HttpStatus.OK);

    }

    @Override
    public ResponseEntity<AssignedProjects> addUserToATeam(long teamId, AssignedProjectRequestBody assignedProjectRequestBody) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCapacityOfUser(long id,int capacity ) {
        AssignedProjects assignedProjects=this.assignedProjectsRepository.findById(id).orElse(null);
        if(assignedProjects==null){
            String message="Assignee not Found.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        assignedProjects.setCapacity(capacity);
        this.assignedProjectsRepository.save(assignedProjects);
        String message="User updated successfully.";
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> removeUserFromTeam(long id) {
        AssignedProjects assignedProjects=this.assignedProjectsRepository.findById(id).orElse(null);
        if(assignedProjects==null){
            String message="Assignee not Found.";
            return new ResponseEntity<>(message, HttpStatus.OK);
        }
        assignedProjects.setActive(false);
        this.assignedProjectsRepository.save(assignedProjects);
        String message="User Removed successfully.";
        return new ResponseEntity<>(message, HttpStatus.OK);


    }
}
