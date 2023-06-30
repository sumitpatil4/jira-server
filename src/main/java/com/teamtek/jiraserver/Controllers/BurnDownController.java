package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.BurnDown;
import com.teamtek.jiraserver.Services.BurnDownService;
import com.teamtek.jiraserver.Utils.BurnDownRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/burnDown")
public class BurnDownController {
    @Autowired
    private BurnDownService burnDownService;

    @PostMapping("/create")
    public ResponseEntity<BurnDown> createBurnDown(@RequestBody BurnDownRequestBody burnDownRequestBody){
        return this.burnDownService.createBurnDown(burnDownRequestBody);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BurnDown> getBurnDownId(@PathVariable("id") Long id){
        return this.burnDownService.getBurnDownById(id);
    }

    @GetMapping("/issue/{id}")
    public ResponseEntity<List<BurnDown>> getBurnDownIssues(@PathVariable("id") Long issueId){
        return this.burnDownService.getBurnDownListByIssue(issueId);
    }

    @PutMapping("/update/{id}/hours/{hours}")
    public ResponseEntity<BurnDown> updateBurnDown(@PathVariable("id") Long id,@PathVariable("hours") Integer hours){
        return this.burnDownService.updateBurnDownById(id,hours);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<BurnDown> deleteBurnDownById(@PathVariable("id") Long id){
        return this.burnDownService.deleteBurnDownById(id);
    }
}
