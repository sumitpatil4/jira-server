package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Teams;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    public ResponseEntity<List<Teams>> getAllTeams();
    public ResponseEntity<Optional<Teams>> getTeamById(Long id);
    public ResponseEntity<Teams> saveTeam(Teams team);
    public ResponseEntity<Teams> deleteTeams(Long id);

}
