package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Repository.TeamRepository;
import com.teamtek.jiraserver.Services.TeamService;
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

    public ResponseEntity<List<Teams>> getAllTeams() {
        return new ResponseEntity<List<Teams>>(teamRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<Optional<Teams>> getTeamById(Long id) {
        return new ResponseEntity<Optional<Teams>>(teamRepository.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<Teams> saveTeam(Teams teams) {
        Teams newTeam = this.teamRepository.save(teams);
        return new ResponseEntity<>(newTeam, HttpStatus.OK);
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
