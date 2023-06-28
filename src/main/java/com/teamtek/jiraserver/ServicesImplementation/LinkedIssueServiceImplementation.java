package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.LinkedIssue;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Repository.LinkedIssueRepository;
import com.teamtek.jiraserver.Services.LinkedIssueService;
import com.teamtek.jiraserver.Utils.LinkedIssueBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LinkedIssueServiceImplementation implements LinkedIssueService {
    @Autowired
    private LinkedIssueRepository linkedIssueRepository;
    @Autowired
    private IssuesRepository issuesRepository;

    @Override
    public ResponseEntity<LinkedIssue> createNewLinkedIssue(LinkedIssueBody linkedIssueBody) {
        try {
            Issues causeIssue = this.issuesRepository.findById(linkedIssueBody.getCauseIssueId()).orElseThrow(null);
            Issues needIssue = this.issuesRepository.findById(linkedIssueBody.getNeedIssueId()).orElseThrow(null);
            LinkedIssue linkedIssue = new LinkedIssue();
            linkedIssue.setCauseIssue(causeIssue);
            linkedIssue.setNeedIssue(needIssue);
            linkedIssue.setDescription(linkedIssueBody.getDescription());
            this.linkedIssueRepository.save(linkedIssue);
            return new ResponseEntity<>(linkedIssue, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<LinkedIssue> getLinkedIssueById(Long id) {
        try {
            LinkedIssue linkedIssue = this.linkedIssueRepository.findByIdAndActive(id, true).orElseThrow(null);
            return new ResponseEntity<>(linkedIssue, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<LinkedIssue>> getAllLinkedIssues() {
        List<LinkedIssue> linkedIssues = this.linkedIssueRepository.findAllByActive(true);
        return new ResponseEntity<>(linkedIssues, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<LinkedIssue>> getAllLinkedIssuesByCauseIssue(Long id) {
        try {
            Issues causeIssue = this.issuesRepository.findById(id).orElseThrow(null);
            List<LinkedIssue> linkedIssues = this.linkedIssueRepository.findAllByCauseIssueAndActive(causeIssue, true);
            return new ResponseEntity<>(linkedIssues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<LinkedIssue>> getAllLinkedIssuesByNeedIssue(Long id) {
        try {
            Issues needIssue = this.issuesRepository.findById(id).orElseThrow(null);
            List<LinkedIssue> linkedIssues = this.linkedIssueRepository.findAllByNeedIssueAndActive(needIssue, true);
            return new ResponseEntity<>(linkedIssues, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<LinkedIssue> updateLinkedIssue(Long id, LinkedIssueBody linkedIssueBody) {
        try {
//            Issues causeIssue = this.issuesRepository.findById(linkedIssueBody.getCauseIssueId()).orElseThrow(null);
            Issues needIssue = this.issuesRepository.findById(linkedIssueBody.getCauseIssueId()).orElseThrow(null);
            LinkedIssue linkedIssue = this.linkedIssueRepository.findById(id).orElseThrow(null);
//            linkedIssue.setCauseIssue(causeIssue);
            linkedIssue.setNeedIssue(needIssue);
            linkedIssue.setDescription(linkedIssueBody.getDescription());
            this.linkedIssueRepository.save(linkedIssue);
            return new ResponseEntity<>(linkedIssue, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<LinkedIssue> deleteLinkedIssue(Long id) {
        try {
            LinkedIssue linkedIssue = this.linkedIssueRepository.findByIdAndActive(id, true).orElseThrow(null);
            linkedIssue.setActive(false);
            this.linkedIssueRepository.save(linkedIssue);
            return new ResponseEntity<>(linkedIssue, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
