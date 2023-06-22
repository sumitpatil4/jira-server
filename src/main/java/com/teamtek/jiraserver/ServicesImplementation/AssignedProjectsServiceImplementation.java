package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Repository.RolesRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class AssignedProjectsServiceImplementation implements AssignedProjectsService  {

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;



    @Autowired
    private UserServiceImple userServiceImple;


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

        AssignedProjects newAssignedProjects = new AssignedProjects();

        String email = assignedProjectRequestBody.getEmail();
        Long roleId = assignedProjectRequestBody.getRoleId();
        int capacity = assignedProjectRequestBody.getCapacity();
        LocalDate endDate = assignedProjectRequestBody.getEndDate();

        Optional<Users> user = userRepository.findByEmail(email);

        if(user==null)
            userServiceImple.addUserToTeam(email);
        else
            newAssignedProjects.setUsers(user);

        Roles roles =


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
