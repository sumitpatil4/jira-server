package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Utils.CreateIssueBody;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssuesService {
    Issues createNewIssue(CreateIssueBody createIssueBody);
    List<Issues> getAllIssues();
}
