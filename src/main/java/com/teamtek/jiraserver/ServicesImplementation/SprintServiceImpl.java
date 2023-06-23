package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Sprints;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Repository.ProjectRepository;
import com.teamtek.jiraserver.Repository.SprintRepository;
import com.teamtek.jiraserver.Services.SprintService;
import com.teamtek.jiraserver.Utils.SprintRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SprintServiceImpl implements SprintService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private IssuesRepository issuesRepository;

    @Override
    public ResponseEntity<Sprints> createSprint(Long projectId) {
        try{
            Projects project = projectRepository.findById(projectId).orElseThrow(null);
            Sprints newSprint = new Sprints();
            int curr_sprint_number = project.getSprintNumber();
            newSprint.setTitle("Sprint "+(curr_sprint_number));
            newSprint.setProject(project);
            sprintRepository.save(newSprint);
            project.setSprintNumber(curr_sprint_number+1);
            projectRepository.save(project);
            return new ResponseEntity<>(newSprint,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Sprints> startSprint(SprintRequestBody sprintRequestBody) {
        try {
            Projects project = projectRepository.findById(sprintRequestBody.getProjectId()).orElseThrow(null);
            if(sprintRequestBody.getEndDate().compareTo(project.getEndDate())>0 ||
                sprintRequestBody.getStartDate().compareTo(project.getStartDate())<0){
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            Sprints sprint =  sprintRepository.findById(sprintRequestBody.getId()).orElseThrow(null);
            sprint.setStartDate(sprintRequestBody.getStartDate());
            sprint.setEndDate(sprintRequestBody.getEndDate());
            sprint.setSprintGoal(sprintRequestBody.getSprintGoal());
            sprint.setCompleted(false);
            sprint.setActive(true);
            sprintRepository.save(sprint);
            project.setActiveSprint(sprint);
            projectRepository.save(project);
            return new ResponseEntity<>(sprint, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Sprints>> getSprintsByProject(Long projectId) {
        try{
            Projects project = projectRepository.findById(projectId).orElseThrow(null);
            List<Sprints> sprintsList = sprintRepository.findByProject(project);
            sprintsList = sprintsList.stream().filter(sprint -> sprint.isActive()!=false).collect(Collectors.toList());
            return new ResponseEntity<>(sprintsList,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Sprints> getSprintById(Long sprintId) {
        try{
            Sprints sprint = sprintRepository.findById(sprintId).orElseThrow(null);
            return new ResponseEntity<>(sprint,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Sprints> updateSprint(SprintRequestBody sprintRequestBody) {
        try{
            Sprints sprint = sprintRepository.findById(sprintRequestBody.getId()).orElseThrow(null);
            sprint.setStartDate(sprintRequestBody.getStartDate());
            sprint.setEndDate(sprintRequestBody.getEndDate());
            sprint.setSprintGoal(sprintRequestBody.getSprintGoal());
            sprintRepository.save(sprint);
            return new ResponseEntity<>(sprint,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Sprints> markAsCompleted(Long sprintId) {
        try{
            Sprints sprint = sprintRepository.findById(sprintId).orElseThrow(null);
            if(sprint.getProject().getActiveSprint().getId()!=sprintId){
                return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
            }
            sprint.setCompleted(true);
            Projects project = projectRepository.findById(sprint.getProject().getId()).orElseThrow(null);
            project.setActiveSprint(null);
            projectRepository.save(project);
            return new ResponseEntity<>(sprint,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Sprints> deleteSprint(Long sprintId) {
        try{
            Sprints sprint = sprintRepository.findById(sprintId).orElseThrow(null);
            List<Issues> issuesList = issuesRepository.findBySprint(sprint);
            Projects project = projectRepository.findById(sprint.getProject().getId()).orElseThrow(null);
            project.setSprintNumber(project.getSprintNumber()-1);
            projectRepository.save(project);
            for(Issues issue : issuesList){
                issue.setSprint(null);
                issuesRepository.save(issue);
            }
            sprint.setActive(false);
            sprintRepository.save(sprint);
            return new ResponseEntity<>(sprint,HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
