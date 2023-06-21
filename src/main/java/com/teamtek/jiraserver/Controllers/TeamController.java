package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Services.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/teams")
public class TeamController {

        @Autowired
        private TeamService teamService;


        @GetMapping
        public ResponseEntity<List<Teams>> getAllTeams() {
            return teamService.getAllTeams();
        }

        @GetMapping("/{id}")
        public ResponseEntity<Optional<Teams>> getTeamById(@PathVariable("id") Long id) {
          return teamService.getTeamById(id);
        }

        @PostMapping
        public ResponseEntity<Teams> saveTeam(@RequestBody Teams team) {
           return teamService.saveTeam(team);
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Teams> deleteTeam (@PathVariable("id") Long id) {
           return teamService.deleteTeams(id);
        }
    }

