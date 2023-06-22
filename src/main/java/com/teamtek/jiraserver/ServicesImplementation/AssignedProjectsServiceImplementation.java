package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Repository.RolesRepository;
import com.teamtek.jiraserver.Repository.TeamRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Utils.AssignedProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    private TeamRepository teamRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private UserServiceImple userServiceImple;


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

        String email = assignedProjectRequestBody.getEmail();
        Long roleId = assignedProjectRequestBody.getRoleId();
        int capacity = assignedProjectRequestBody.getCapacity();
        LocalDate endDate = assignedProjectRequestBody.getEndDate();

        Optional<Users> user = userRepository.findByEmail(email);

        if(user.isEmpty()) {
            userServiceImple.addUserToTeam(email);
            Optional<Users> user1 = userRepository.findByEmail(email);
            AssignedProjects newAssignedProjects = new AssignedProjects();
            Optional<Teams> team = teamRepository.findById(teamId);
            Optional<Roles> role = rolesRepository.findById(roleId);
            Users users=user1.orElseThrow();
            Teams teams = team.orElseThrow();
            Roles roles = role.orElseThrow();

            newAssignedProjects.setUsers(users);
            newAssignedProjects.setTeam(teams);
            newAssignedProjects.setRole(roles);
            newAssignedProjects.setCapacity(capacity);
            newAssignedProjects.setEndDate(endDate);

            AssignedProjects assignedProjects=assignedProjectsRepository.save(newAssignedProjects);
            return new ResponseEntity<>(assignedProjects,HttpStatus.OK);

        }
        else{
            AssignedProjects newAssignedProjects = new AssignedProjects();
            Optional<Teams> team = teamRepository.findById(teamId);
            Optional<Roles> role = rolesRepository.findById(roleId);
            Users users=user.orElseThrow();
            Teams teams = team.orElseThrow();
            Roles roles = role.orElseThrow();

            newAssignedProjects.setUsers(users);
            newAssignedProjects.setTeam(teams);
            newAssignedProjects.setRole(roles);
            newAssignedProjects.setCapacity(capacity);
            newAssignedProjects.setEndDate(endDate);

            AssignedProjects assignedProjects=assignedProjectsRepository.save(newAssignedProjects);
            return new ResponseEntity<>(assignedProjects,HttpStatus.OK);
        }
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
