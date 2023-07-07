package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.*;
import com.teamtek.jiraserver.Repository.*;
import com.teamtek.jiraserver.Services.BoardSequenceService;
import com.teamtek.jiraserver.Services.IssuesService;
import com.teamtek.jiraserver.Utils.IssuesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @Autowired
    private LinkedIssueRepository linkedIssueRepository;

    @Autowired
    private BoardSequenceService boardSequenceService;

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
            issues.setIssueName("I "+project.getIssueNumber());
            if(issuesRequestBody.getDescription()!=null){
                issues.setDescription(issuesRequestBody.getDescription());
            }
            if(issuesRequestBody.getCompletionDate()!=null){
                issues.setCompletionDate(issuesRequestBody.getCompletionDate());
            }
            if (issuesRequestBody.getSprintId() != null) {
                Sprints sprint = this.sprintRepository.findById(issuesRequestBody.getSprintId()).orElseThrow(null);
            }
            //Logic to add issue stage
            Pageable pageable = PageRequest.of(0,1);
            IssueStages issueStages = issueStagesRepository.findIssueStageWithLeastHierarchy(project, pageable).get(0);
            issues.setIssueStage(issueStages);

            if(issuesRequestBody.getAssignedToId()!=null){
                Users assignedTo = this.userRepository.findById(issuesRequestBody.getAssignedToId()).orElseThrow(null);
                issues.setAssignedTo(assignedTo);
            }
            if(issuesRequestBody.getParentIssueId()!=null){
                Issues parentIssue = this.issuesRepository.findById(issuesRequestBody.getParentIssueId()).orElseThrow(null);
                issues.setParentIssue(parentIssue);
            }
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
            this.issuesRepository.save(issues);
            if(issuesRequestBody.getSprintId()!=null)
                this.boardSequenceService.assignBoardSequenceNumber(issues);
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
            Projects project = this.projectRepository.findById(issuesRequestBody.getProjectId()).orElseThrow(null);
            issues.setProject(project);
            IssueTypes issueTypes = this.issueTypesRepository.findAllByProjectAndLevelAndActive(project, 1, true).get(0);
            issues.setIssueType(issueTypes);
            Users createdBy = this.userRepository.findById(issuesRequestBody.getCreatedById()).orElseThrow(null);
            issues.setCreatedBy(createdBy);
            issues.setIssueName("I "+project.getIssueNumber());
            if(issuesRequestBody.getDescription()!=null){
                issues.setDescription(issuesRequestBody.getDescription());
            }
            if(issuesRequestBody.getCompletionDate()!=null){
                issues.setCompletionDate(issuesRequestBody.getCompletionDate());
            }
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
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
            Projects project = this.projectRepository.findById(issuesRequestBody.getProjectId()).orElseThrow(null);
            issues.setProject(project);
            IssueTypes issueTypes = this.issueTypesRepository.findAllByProjectAndLevelAndActive(project, 3, true).get(0);
            issues.setIssueType(issueTypes);
            Users createdBy = this.userRepository.findById(issuesRequestBody.getCreatedById()).orElseThrow(null);
            issues.setCreatedBy(createdBy);
            issues.setIssueName("I "+project.getIssueNumber());
            Issues parentIssue = this.issuesRepository.findById(issuesRequestBody.getParentIssueId()).orElseThrow(null);
            if(parentIssue.getAssignedTo()!=null) {
                issues.setAssignedTo(parentIssue.getAssignedTo());
            }
            issues.setParentIssue(parentIssue);
            issues.setEstimatedTime(issuesRequestBody.getEstimatedTime());
            //Logic to add issue stage
            Pageable pageable = PageRequest.of(0,1);
            IssueStages issueStages = issueStagesRepository.findIssueStageWithLeastHierarchy(project, pageable).get(0);
            issues.setIssueStage(issueStages);
            if(issuesRequestBody.getDescription()!=null){
                issues.setDescription(issuesRequestBody.getDescription());
            }
            if(parentIssue.getSprint()!=null) {
                issues.setSprint(parentIssue.getSprint());
            }
            project.setIssueNumber(project.getIssueNumber()+1);
            this.projectRepository.save(project);
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Issues> updateIssue(IssuesRequestBody issuesRequestBody) {
        try
        {
            Issues issues = this.issuesRepository.findById(issuesRequestBody.getId()).orElseThrow(null);
            if(issuesRequestBody.getTitle()!=null){
                issues.setTitle(issuesRequestBody.getTitle());
            }
            if(issuesRequestBody.getDescription()!=null){
                issues.setDescription(issuesRequestBody.getDescription());
            }
            if(issuesRequestBody.getCompletionDate()!=null){
                issues.setCompletionDate(issuesRequestBody.getCompletionDate());
            }
            if(issuesRequestBody.getStoryPoints()!=null){
                issues.setStoryPoints(issuesRequestBody.getStoryPoints());
            }
            if(issuesRequestBody.getEstimatedTime()!=null){
                issues.setEstimatedTime(issuesRequestBody.getEstimatedTime());
            }
            if(issuesRequestBody.getIssueTypeId()!=null){
                IssueTypes issueTypes = this.issueTypesRepository.findById(issuesRequestBody.getIssueTypeId()).orElseThrow(null);
                issues.setIssueType(issueTypes);
            }
            if(issuesRequestBody.getIssueStageId()!=null){
                IssueStages issueStages = this.issueStagesRepository.findById(issuesRequestBody.getIssueStageId()).orElseThrow(null);
                issues.setIssueStage(issueStages);
            }
            if(issuesRequestBody.getAssignedToId()!=null){
                Users assignedTo = this.userRepository.findById(issuesRequestBody.getAssignedToId()).orElseThrow(null);
                issues.setAssignedTo(assignedTo);
            }
            if(issuesRequestBody.getSprintId()!=null){
                Sprints sprints = this.sprintRepository.findById(issuesRequestBody.getSprintId()).orElseThrow(null);
                issues.setSprint(sprints);
            }
            this.issuesRepository.save(issues);
            this.boardSequenceService.assignBoardSequenceNumber(issues);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<Issues> deleteIssue(Long id)
    {
        try
        {
            Issues issues = this.issuesRepository.findById(id).orElseThrow(null);
            IssueTypes issueTypes=issues.getIssueType();
            Integer level=issueTypes.getLevel();
            List<Issues> childIssues=this.issuesRepository.findByParentIssue(issues);
            List<LinkedIssue> causeIssues=this.linkedIssueRepository.findAllByCauseIssue(issues);
            List<LinkedIssue> needIssues=this.linkedIssueRepository.findAllByNeedIssue(issues);
            for(int i=0; i<causeIssues.size(); i++){
                causeIssues.get(i).setActive(false);
                this.linkedIssueRepository.save(causeIssues.get(i));
            }
            for(int i=0; i<needIssues.size(); i++){
                needIssues.get(i).setActive(false);
                this.linkedIssueRepository.save(needIssues.get(i));
            }
            if(level==1)
            {
                for (int i=0;i<childIssues.size();i++)
                {
                    childIssues.get(i).setParentIssue(null);
                    this.issuesRepository.save(childIssues.get(i));
                    causeIssues=this.linkedIssueRepository.findAllByCauseIssue(childIssues.get(i));
                    needIssues=this.linkedIssueRepository.findAllByNeedIssue(childIssues.get(i));
                    for(int j=0; j<causeIssues.size(); j++){
                        causeIssues.get(j).setActive(false);
                        this.linkedIssueRepository.save(causeIssues.get(j));
                    }
                    for(int j=0; j<needIssues.size(); j++){
                        needIssues.get(j).setActive(false);
                        this.linkedIssueRepository.save(needIssues.get(j));
                    }
                }
            }
            if(level==2)
            {
                for (int i=0;i<childIssues.size();i++)
                {
                    childIssues.get(i).setActive(false);
                    this.issuesRepository.save(childIssues.get(i));
                    causeIssues=this.linkedIssueRepository.findAllByCauseIssue(childIssues.get(i));
                    needIssues=this.linkedIssueRepository.findAllByNeedIssue(childIssues.get(i));
                    for(int j=0; j<causeIssues.size(); j++){
                        causeIssues.get(j).setActive(false);
                        this.linkedIssueRepository.save(causeIssues.get(j));
                    }
                    for(int j=0; j<needIssues.size(); j++){
                        needIssues.get(j).setActive(false);
                        this.linkedIssueRepository.save(needIssues.get(j));
                    }
                }
            }
            issues.setActive(false);
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<List<Issues>> getAllIssues() {
        try {
            List<Issues> issues = this.issuesRepository.findAll();
            return new ResponseEntity<>(issues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public ResponseEntity<Issues> getIssueById(Long id) {
        try {
            Issues issues = this.issuesRepository.findById(id).orElseThrow(null);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Integer> noOfIssuesBySprint(Long id) {
        try{
            Sprints sprints = this.sprintRepository.findById(id).orElseThrow(null);
            Integer noOfIssues = this.issuesRepository.noOfIssuesBySprint(sprints);
            return new ResponseEntity<>(noOfIssues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Issues> pushIssueToBacklog(Long id) {
        try {
            Issues issues = this.issuesRepository.findById(id).orElseThrow(null);
            issues.setSprint(null);
            this.issuesRepository.save(issues);
            return new ResponseEntity<>(issues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}

