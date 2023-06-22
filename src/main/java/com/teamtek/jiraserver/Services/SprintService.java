package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Sprints;
import com.teamtek.jiraserver.Utils.SprintRequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SprintService {
    public ResponseEntity<Sprints> createSprint(Long projectId);
    public ResponseEntity<Sprints> startSprint(SprintRequestBody sprintRequestBody);
    public ResponseEntity<List<Sprints>> getSprintsByProject(Long projectId);
    public ResponseEntity<Sprints> getSprintById(Long sprintId);
    public ResponseEntity<Sprints> updateSprint(SprintRequestBody sprintRequestBody);
    public ResponseEntity<Sprints> markAsCompleted(Long sprintId);
    public ResponseEntity<Sprints> deleteSprint(Long sprintId);
}
