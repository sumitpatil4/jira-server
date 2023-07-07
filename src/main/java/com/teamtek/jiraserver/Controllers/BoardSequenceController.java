package com.teamtek.jiraserver.Controllers;


import com.teamtek.jiraserver.Model.BoardSequence;
import com.teamtek.jiraserver.Model.BurnDown;
import com.teamtek.jiraserver.Services.BoardSequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/boardsequence")
public class BoardSequenceController {

    @Autowired
    private BoardSequenceService boardSequenceService;

    @GetMapping("/{id}")
    public ResponseEntity<BoardSequence> getBoardSequenceId(@PathVariable("id") Long id){
        return this.boardSequenceService.getBoardSequenceById(id);
    }

    @PutMapping("/ordersequence/{issue}/start/{start}/end/{end}")
    public ResponseEntity<String> updateSequence(@PathVariable("project") Long issue, @PathVariable("start") Integer start, @PathVariable("end") Integer end){
        return this.boardSequenceService.updateSequence(issue, issue,start,issue, end);
    }


}
