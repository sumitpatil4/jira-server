package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Services.IssuesService;
import com.teamtek.jiraserver.Utils.IssuesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<Issues>> getAllIssues(){
        return this.issuesService.getAllIssues();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Issues> getIssueById(@PathVariable("id") Long id){
        return this.issuesService.getIssueById(id);
    }

    @PutMapping
    public ResponseEntity<Issues> updateIssues(@RequestBody IssuesRequestBody issuesRequestBody){
        return this.issuesService.updateIssue(issuesRequestBody);
    }

    @PutMapping("/pushToBacklog/{id}")
    public ResponseEntity<Issues> pushIssuesToBacklog(@PathVariable("id") Long id){
        return this.issuesService.pushIssueToBacklog(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Issues> deleteIssues(@PathVariable("id") Long id){
        return this.issuesService.deleteIssue(id);
    }

    @GetMapping("/noOfIssuesBySprint/{id}")
    public ResponseEntity<Integer> noOfIssuesBySprint(@PathVariable("id") Long id){
        return this.issuesService.noOfIssuesBySprint(id);
    }
}