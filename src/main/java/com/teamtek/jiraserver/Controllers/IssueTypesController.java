package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.IssueTypes;
import com.teamtek.jiraserver.Services.IssueTypesService;
import com.teamtek.jiraserver.Utils.IssueTypesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/api/issue-types")
public class IssueTypesController {

    @Autowired
    private IssueTypesService issueTypesService;

    @PostMapping
    public ResponseEntity<IssueTypes> createNewIssueType(@RequestBody IssueTypesRequestBody issueTypesRequestBody)
    {
        return this.issueTypesService.createNewIssueType(issueTypesRequestBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<IssueTypes>> findByProject(@PathVariable("id") Long projectId){
        return this.issueTypesService.findByProject(projectId);
    }

    @GetMapping("/project/{id}/level/{level}")
    public ResponseEntity<List<IssueTypes>> findByProjectAndLevel(@PathVariable("id") Long projectId, @PathVariable("level") Integer level){
        return this.issueTypesService.findByProjectAndLevel(projectId, level);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<IssueTypes> deleteIssueType(@PathVariable("id") Long id){
        return this.issueTypesService.deleteIssueType(id);
    }

}
