package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Services.IssueStagesService;
import com.teamtek.jiraserver.Utils.IssueStagesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issue-stages")
@CrossOrigin(origins= "*", allowedHeaders = "*")
public class IssueStagesController {
    @Autowired
    private IssueStagesService issueStagesService;

    @PostMapping
    public ResponseEntity<IssueStages> addNewIssueStage(@RequestBody IssueStagesRequestBody issueStagesRequestBody){
//        System.out.println(issueStagesRequestBody.getTitle());
        return this.issueStagesService.addNewIssueStage(issueStagesRequestBody);
    }

    @GetMapping("/project/{id}")
    public ResponseEntity<List<IssueStages>> findByProject(@PathVariable("id") Long id){
        return this.issueStagesService.findByProject(id);
    }

    @GetMapping("/project/least/{id}")
    public ResponseEntity<IssueStages> findByProjectWithLeastHierarchy(@PathVariable("id") Long id){
        return this.issueStagesService.findLeastHierarchyStageOfProject(id);
    }

    @GetMapping("/project/highest/{id}")
    public ResponseEntity<IssueStages> findByProjectWithHighestHierarchy(@PathVariable("id") Long id){
        return this.issueStagesService.findHighestHierarchyStageOfProject(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IssueStages> deleteIssueStages(@PathVariable("id") Long id){
        return this.issueStagesService.deleteIssueStage(id);
    }

    @PutMapping("/project/{project}/start/{start}/end/{end}")
    public ResponseEntity<String> updateHierarchy(@PathVariable("project") Long project, @PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return this.issueStagesService.updateHierarchy(project, start, end);
    }
}
