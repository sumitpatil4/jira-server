package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AssignedProjectsServiceImplementation implements AssignedProjectsService  {

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;


    @Override
    public ResponseEntity<List<AssignedProjects>> getAllUsersOfATeam(long id) {
        return null;
    }

    @Override
    public ResponseEntity<AssignedProjects> getDetailofAssigned(long id) {
        return null;
    }

    @Override
    public ResponseEntity<AssignedProjects> addUserToATeam(long teamId, AssignedProjectRequestBody assignedProjectRequestBody) {
        return null;
    }

    @Override
    public ResponseEntity<String> updateCapacityOfUser(long id) {
        return null;
    }

    @Override
    public ResponseEntity<String> removeUserFromTeam(long id) {
        return null;
    }
}
