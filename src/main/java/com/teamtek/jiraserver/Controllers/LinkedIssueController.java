package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.LinkedIssue;
import com.teamtek.jiraserver.Services.LinkedIssueService;
import com.teamtek.jiraserver.Utils.LinkedIssueBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/linkedIssue")
public class LinkedIssueController {
    @Autowired
    private LinkedIssueService linkedIssueService;

    @PostMapping
    ResponseEntity<LinkedIssue> createLinkedIssue(@RequestBody LinkedIssueBody linkedIssueBody){
        return this.linkedIssueService.createNewLinkedIssue(linkedIssueBody);
    }

    @GetMapping("/{id}")
    ResponseEntity<LinkedIssue> getLinkedIssueById(@PathVariable("id") Long id){
        return this.linkedIssueService.getLinkedIssueById(id);
    }

    @GetMapping
    ResponseEntity<List<LinkedIssue>> getAllLinkedIssues(){
        return this.linkedIssueService.getAllLinkedIssues();
    }

    @GetMapping("/causeIssue/{id}")
    ResponseEntity<List<LinkedIssue>> getLinkedIssuesByCauseIssue(@PathVariable("id") Long id){
        return this.linkedIssueService.getAllLinkedIssuesByCauseIssue(id);
    }

    @GetMapping("/needIssue/{id}")
    ResponseEntity<List<LinkedIssue>> getLinkedIssuesByNeedIssue(@PathVariable("id") Long id){
        return this.linkedIssueService.getAllLinkedIssuesByNeedIssue(id);
    }

    @PutMapping("/{id}")
    ResponseEntity<LinkedIssue> updateLinkedIssue(@PathVariable("id") Long id, @RequestBody LinkedIssueBody linkedIssueBody){
        return this.linkedIssueService.updateLinkedIssue(id, linkedIssueBody);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<LinkedIssue> deleteLinkedIssue(@PathVariable("id") Long id){
        return this.linkedIssueService.deleteLinkedIssue(id);
    }
}
