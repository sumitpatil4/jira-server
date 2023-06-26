package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.LinkedIssue;
import com.teamtek.jiraserver.Repository.LinkedIssueRepository;
import com.teamtek.jiraserver.Utils.LinkedIssueBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LinkedIssueService {
    ResponseEntity<LinkedIssue> createNewLinkedIssue(LinkedIssueBody linkedIssueBody);
    ResponseEntity<LinkedIssue> getLinkedIssueById(Long id);
    ResponseEntity<List<LinkedIssue>> getAllLinkedIssues();
    ResponseEntity<List<LinkedIssue>> getAllLinkedIssuesByCauseIssue(Long id);
    ResponseEntity<List<LinkedIssue>> getAllLinkedIssuesByNeedIssue(Long id);
    ResponseEntity<LinkedIssue> updateLinkedIssue(Long id, LinkedIssueBody linkedIssueBody);
    ResponseEntity<LinkedIssue> deleteLinkedIssue(Long id);
}
