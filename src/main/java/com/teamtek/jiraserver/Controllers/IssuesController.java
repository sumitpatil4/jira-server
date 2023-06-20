package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Services.IssuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {
    @Autowired
    private IssuesService issuesService;
}