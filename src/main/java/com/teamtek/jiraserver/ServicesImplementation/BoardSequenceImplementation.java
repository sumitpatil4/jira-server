package com.teamtek.jiraserver.ServicesImplementation;


import com.teamtek.jiraserver.Model.*;
import com.teamtek.jiraserver.Repository.*;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import com.teamtek.jiraserver.Services.BoardSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class BoardSequenceImplementation implements BoardSequenceService {

    @Autowired
    private BoardSequenceRepository boardSequenceRepository;

    @Autowired
    private IssuesRepository issuesRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssueStagesRepository issueStagesRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private AssignedProjectsService assignedProjectsService;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;

    @Override
    public ResponseEntity<BoardSequence> getBoardSequenceById(Long id) {
        try{
            BoardSequence boardSequence = this.boardSequenceRepository.findById(id).orElseThrow(null);
            return new ResponseEntity<>(boardSequence, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> assignBoardSequenceNumber(Issues issues) {
        try{
            List<Issues>issuesList=this.issuesRepository.findByIssueStageAndSprintAndProject(issues.getIssueStage(),issues.getSprint(),issues.getProject());
            Long newSequence=issuesList.stream().count()+1;
            List<Teams>teamsList=this.teamRepository.findByProject(issues.getProject());
            for (Teams team : teamsList) {
                List<AssignedProjects>assignedProjectsList=this.assignedProjectsRepository.findByTeam(team);
                for(AssignedProjects project:assignedProjectsList){
                    Users user=project.getUsers();
                    BoardSequence boardSequence = new BoardSequence();
                    boardSequence.setSequence(newSequence);
                    boardSequence.setIssues(issues);
                    boardSequence.setUsers(user);
                    this.boardSequenceRepository.save(boardSequence);
                }
            }
            return new ResponseEntity<>("sequence assigned successfully",HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateSequence(Long issuesId,Long issueStageId1, Integer start,Long issueStageId2, Integer end) {
        try{
            Issues issues=this.issuesRepository.findById(issuesId).orElseThrow(null);
            if(issueStageId1==issueStageId2) {
                List<Issues>issuesList=this.issuesRepository.findByIssueStageAndSprintAndProject(issues.getIssueStage(),issues.getSprint(),issues.getProject());
                Collections.sort(issuesList, (i1, i2) -> {
                    BoardSequence sq1=boardSequenceRepository.findByIssuesAndUsers(i1,i1.getAssignedTo());
                    BoardSequence sq2=boardSequenceRepository.findByIssuesAndUsers(i2,i2.getAssignedTo());
                    return sq1.getSequence().intValue() - sq2.getSequence().intValue();
                });
                if(start<end){
                    BoardSequence boardSequence=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(start),issuesList.get(start).getAssignedTo());
                    for(int i=start+1;i<=end;i++)
                    {
                        BoardSequence boardSequence1=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(i),issuesList.get(i).getAssignedTo());
                        boardSequence1.setSequence(Long.valueOf(i-1));
                        this.boardSequenceRepository.save(boardSequence1);
                    }
                    boardSequence.setSequence(Long.valueOf(end));
                    this.boardSequenceRepository.save(boardSequence);
                }
                if(start>end){
                    BoardSequence boardSequence=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(end),issuesList.get(end).getAssignedTo());
                    for(int i=start;i<end;i++)
                    {
                        BoardSequence boardSequence1=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(i),issuesList.get(i).getAssignedTo());
                        boardSequence1.setSequence(Long.valueOf(i+1));
                        this.boardSequenceRepository.save(boardSequence1);
                    }
                    boardSequence.setSequence(Long.valueOf(start));
                    this.boardSequenceRepository.save(boardSequence);
                }
            }
            else{
                IssueStages issueStages=this.issueStagesRepository.findById(issueStageId2).orElseThrow(null);
                List<Issues>issuesList=this.issuesRepository.findByIssueStageAndSprintAndProject(issueStages,issues.getSprint(),issues.getProject());
                Collections.sort(issuesList, (i1, i2) -> {
                    BoardSequence sq1=boardSequenceRepository.findByIssuesAndUsers(i1,i1.getAssignedTo());
                    BoardSequence sq2=boardSequenceRepository.findByIssuesAndUsers(i2,i2.getAssignedTo());
                    return sq1.getSequence().intValue() - sq2.getSequence().intValue();
                });
                int issuesListSize=issuesList.size();
                BoardSequence boardSequence=this.boardSequenceRepository.findByIssuesAndUsers(issues,issues.getAssignedTo());
                if(end==0)
                {
                    for(int i=0;i<issuesListSize;i++)
                    {
                        BoardSequence boardSequence1=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(i),issuesList.get(i).getAssignedTo());
                        boardSequence1.setSequence(Long.valueOf(i+1));
                        this.boardSequenceRepository.save(boardSequence1);
                    }
                }
                else if(end<issuesListSize){
                    for(int i=end;i<issuesListSize;i++)
                    {
                        BoardSequence boardSequence1=this.boardSequenceRepository.findByIssuesAndUsers(issuesList.get(i),issuesList.get(i).getAssignedTo());
                        boardSequence1.setSequence(Long.valueOf(i+1));
                        this.boardSequenceRepository.save(boardSequence1);
                    }
                }
                boardSequence.setSequence(Long.valueOf(end));
                issues.setIssueStage(issueStages);
                this.issuesRepository.save(issues);
                this.boardSequenceRepository.save(boardSequence);
            }
            return new ResponseEntity<>("Sequence Updated Successfully",HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
