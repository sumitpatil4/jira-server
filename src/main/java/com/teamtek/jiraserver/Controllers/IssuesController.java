package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Services.IssuesService;
import com.teamtek.jiraserver.Utils.IssuesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/issues")
public class IssuesController {
    @Autowired
    private IssuesService issuesService;

    @PostMapping("/epic")
    public ResponseEntity<Issues> createNewEpic(@RequestBody IssuesRequestBody issuesRequestBody){
        return this.issuesService.createNewEpic(issuesRequestBody);
    }

    @PostMapping("/task")
    public ResponseEntity<Issues> createNewTask(@RequestBody IssuesRequestBody issuesRequestBody){
        return this.issuesService.createNewTask(issuesRequestBody);
    }

    @PostMapping("/subtask")
    public ResponseEntity<Issues> createNewSubtask(@RequestBody IssuesRequestBody issuesRequestBody){
        return this.issuesService.createNewSubtask(issuesRequestBody);
    }
}