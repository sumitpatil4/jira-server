package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.IssueTypes;
import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Utils.IssuesRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssuesService {
    ResponseEntity<Issues> createNewTask(IssuesRequestBody issuesRequestBody);
    ResponseEntity<Issues> createNewEpic(IssuesRequestBody issuesRequestBody);
    ResponseEntity<Issues> createNewSubtask(IssuesRequestBody issuesRequestBody);
    ResponseEntity<Issues> updateIssue(IssuesRequestBody issuesRequestBody);
    ResponseEntity<Issues> deleteIssue(Long id);
    ResponseEntity<List<Issues>> getAllIssues();
    ResponseEntity<Issues> getIssueById(Long id);
}
