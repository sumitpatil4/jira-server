package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.ProjectRepository;
import com.teamtek.jiraserver.Repository.TeamRepository;
import com.teamtek.jiraserver.Services.TeamService;
import com.teamtek.jiraserver.Utils.ProjectRequestBody;
import com.teamtek.jiraserver.Utils.TeamRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamServiceImplementation implements TeamService {

    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private ProjectRepository projectRepository;

    public ResponseEntity<List<Teams>> getAllTeams() {
        return new ResponseEntity<List<Teams>>(teamRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Teams>> getTeamById(Long id) {
        return new ResponseEntity<Optional<Teams>>(teamRepository.findById(id), HttpStatus.OK);
    }


    public ResponseEntity<Teams> addTeam(TeamRequestBody body){
        try {
            Projects project = projectRepository.findById(body.getProjectId()).orElseThrow(null);
            Teams team = new Teams();
            team.setName(body.getName());
            team.setProject(project);
            Teams created = teamRepository.save(team);
            return new ResponseEntity<Teams>(created, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Teams>((Teams) null, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<Teams> deleteTeams(Long id) {
        try {
            Teams teams = teamRepository.findById(id).orElseThrow(null);
            teams.setActive(false);
            Teams updatedTeam = teamRepository.save(teams);
            return new ResponseEntity<>(updatedTeam, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<Teams>((Teams) null, HttpStatus.BAD_REQUEST);
        }

    }
}
