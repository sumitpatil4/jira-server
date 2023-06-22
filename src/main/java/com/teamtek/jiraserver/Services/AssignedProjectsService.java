package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AssignedProjectsService {

    /*
        1. Get all list of users of a team
        2. Add user to a team.
        3. Update Capacity of a user in a team.
        4. Remove user from a team
        5. Get particular user.
        6.
     */

    public ResponseEntity<List<AssignedProjects>> getAllUsersOfATeam(long id);

    public ResponseEntity<AssignedProjects> getDetailofAssigned(long id);

    public ResponseEntity<AssignedProjects> addUserToATeam(long teamId, AssignedProjectRequestBody assignedProjectRequestBody);

    public ResponseEntity<String> updateCapacityOfUser(long id);

    public ResponseEntity<String> removeUserFromTeam(long id);

}
