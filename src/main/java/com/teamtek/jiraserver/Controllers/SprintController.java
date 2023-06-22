package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Sprints;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Services.SprintService;
import com.teamtek.jiraserver.Utils.SprintRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sprint")
public class SprintController {
    @Autowired
    private SprintService sprintService;

    @Autowired
    private IssuesRepository issuesRepository;

    @PostMapping("/createSprint/{projectId}")
    public ResponseEntity<Sprints> createSprint(@PathVariable Long projectId){
        return sprintService.createSprint(projectId);
    }

    @PutMapping("/startSprint")
    public ResponseEntity<Sprints> startSprint(@RequestBody SprintRequestBody sprintRequestBody){
        return sprintService.startSprint(sprintRequestBody);
    }

    @GetMapping("/getSprintsByProject/{projectId}")
    public ResponseEntity<List<Sprints>> getSprintsByProject(@PathVariable Long projectId){
        return sprintService.getSprintsByProject(projectId);
    }

    @GetMapping("/getSprintById/{sprintId}")
    public ResponseEntity<Sprints> getSprintById(@PathVariable Long sprintId){
        return sprintService.getSprintById(sprintId);
    }

    @PutMapping("/updateSprint")
    public ResponseEntity<Sprints> updateSprint(@RequestBody SprintRequestBody sprintRequestBody){
        return sprintService.updateSprint(sprintRequestBody);
    }

    @PutMapping("/markAsCompleted/{sprintId}")
    public ResponseEntity<Sprints> markAsCompleted(@PathVariable Long sprintId){
        return sprintService.markAsCompleted(sprintId);
    }

    @DeleteMapping("/deleteSprint/{sprintId}")
    public ResponseEntity<Sprints> deleteSprints(@PathVariable Long sprintId){
        return sprintService.deleteSprint(sprintId);
    }
}
