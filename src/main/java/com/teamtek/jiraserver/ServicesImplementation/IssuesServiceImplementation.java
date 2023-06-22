package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.*;
import com.teamtek.jiraserver.Repository.*;
import com.teamtek.jiraserver.Services.IssuesService;
import com.teamtek.jiraserver.Utils.IssuesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImplementation implements IssuesService {
    @Autowired
    private IssuesRepository issuesRepository;
    @Autowired
    private IssueTypesRepository issueTypesRepository;
    @Autowired
    private IssueStagesRepository issueStagesRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private SprintRepository sprintRepository;

    @Override
    public ResponseEntity<Issues> createNewTask(IssuesRequestBody issuesRequestBody) {
        try {
            Issues issues = new Issues();
            issues.setTitle(issuesRequestBody.getTitle());
            issues.setStoryPoints(issuesRequestBody.getStoryPoints());
            IssueTypes issueTypes = this.issueTypesRepository.findById(issuesRequestBody.getIssueTypeId()).orElseThrow(null);
            issues.setIssueType(issueTypes);
            Users createdBy = this.userRepository.findById(issuesRequestBody.getCreatedById()).orElseThrow(null);
            issues.setCreatedBy(createdBy);
            Projects project = this.projectRepository.findById(issuesRequestBody.getProjectId()).orElseThrow(null);
            issues.setProject(project);
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
            issues.setIssueName("I "+project.getIssueNumber());
            if (issuesRequestBody.getSprintId() != null) {
                Sprints sprint = this.sprintRepository.findById(issuesRequestBody.getSprintId()).orElseThrow(null);
            }
            //Logic to add issue stage

            if(issuesRequestBody.getAssignedToId()!=null){
                Users assignedTo = this.userRepository.findById(issuesRequestBody.getAssignedToId()).orElseThrow(null);
                issues.setAssignedTo(assignedTo);
            }
            if(issuesRequestBody.getParentIssueId()!=null){
                Issues parentIssue = this.issuesRepository.findById(issuesRequestBody.getParentIssueId()).orElseThrow(null);
                issues.setParentIssue(parentIssue);
            }
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Issues> createNewEpic(IssuesRequestBody issuesRequestBody) {
        try {
            Issues issues = new Issues();
            issues.setTitle(issuesRequestBody.getTitle());
            IssueTypes issueTypes = this.issueTypesRepository.findById(issuesRequestBody.getIssueTypeId()).orElseThrow(null);
            issues.setIssueType(issueTypes);
            Users createdBy = this.userRepository.findById(issuesRequestBody.getCreatedById()).orElseThrow(null);
            issues.setCreatedBy(createdBy);
            Projects project = this.projectRepository.findById(issuesRequestBody.getProjectId()).orElseThrow(null);
            issues.setProject(project);
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
            issues.setIssueName("I "+project.getIssueNumber());
            if(issuesRequestBody.getDescription()!=null){
                issues.setDescription(issuesRequestBody.getDescription());
            }
            if(issuesRequestBody.getCompletionDate()!=null){
                issues.setCompletionDate(issuesRequestBody.getCompletionDate());
            }
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Issues> createNewSubtask(IssuesRequestBody issuesRequestBody) {
        try {
            Issues issues = new Issues();
            issues.setTitle(issuesRequestBody.getTitle());
            IssueTypes issueTypes = this.issueTypesRepository.findById(issuesRequestBody.getIssueTypeId()).orElseThrow(null);
            issues.setIssueType(issueTypes);
            Users createdBy = this.userRepository.findById(issuesRequestBody.getCreatedById()).orElseThrow(null);
            issues.setCreatedBy(createdBy);
            Projects project = this.projectRepository.findById(issuesRequestBody.getProjectId()).orElseThrow(null);
            issues.setProject(project);
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
            issues.setIssueName("I "+project.getIssueNumber());
            Issues parentIssue = this.issuesRepository.findById(issuesRequestBody.getParentIssueId()).orElseThrow(null);
            issues.setParentIssue(parentIssue);
            issues.setEstimatedTime(issuesRequestBody.getEstimatedTime());
            //Logic to add issue stage

            if(parentIssue.getSprint()!=null) {
                issues.setSprint(parentIssue.getSprint());
                issues.setAssignedTo(parentIssue.getAssignedTo());
            }
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
