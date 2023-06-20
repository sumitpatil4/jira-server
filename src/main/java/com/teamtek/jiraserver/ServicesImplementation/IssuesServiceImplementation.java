package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Services.IssuesService;
import com.teamtek.jiraserver.Utils.CreateIssueBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssuesServiceImplementation implements IssuesService {
    @Autowired
    private IssuesRepository issuesRepository;

    @Override
    public Issues createNewIssue(CreateIssueBody createIssueBody) {
        return null;
    }

    @Override
    public List<Issues> getAllIssues() {
        return null;
    }
}
