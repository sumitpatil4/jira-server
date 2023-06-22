package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedProjectsServiceImplementation implements AssignedProjectsService  {

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;

    @Override
    public ResponseEntity<List<AssignedProjects>> getAllUsersOfATeam() {
        return null;
    }

    @Override
    public ResponseEntity<AssignedProjects> getDetailofAssigned() {
        return null;
    }

    @Override
    public ResponseEntity<AssignedProjects> addUserToATeam() {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCapacityOfUser() {
        return null;
    }

    @Override
    public ResponseEntity<String> removeUserFromTeam() {
        return null;
    }
}
