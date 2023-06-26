package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Utils.TeamRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TeamService {
    public ResponseEntity<List<Teams>> getAllTeams();
    public ResponseEntity<Optional<Teams>> getTeamById(Long id);
    public ResponseEntity<Teams> addTeam(TeamRequestBody body);
    public ResponseEntity<Teams> deleteTeams(Long id);

}
