package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.BurnDown;
import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Repository.BurnDownRepository;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Services.BurnDownService;
import com.teamtek.jiraserver.Utils.BurnDownRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BurnDownServiceImplementation implements BurnDownService {
    @Autowired
    private BurnDownRepository burnDownRepository;

    @Autowired
    private IssuesRepository issuesRepository;

    @Override
    public ResponseEntity<BurnDown> createBurnDown(BurnDownRequestBody burnDownRequestBody) {
        try {
            BurnDown burnDown = new BurnDown();
            burnDown.setHours(burnDownRequestBody.getHours());
            Issues issues = this.issuesRepository.findById(burnDownRequestBody.getIssueId()).orElseThrow(null);
            burnDown.setIssue(issues);
            BurnDown newBurnDown = this.burnDownRepository.save(burnDown);
            return new ResponseEntity<>(newBurnDown, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BurnDown> getBurnDownById(Long id) {
        try{
            BurnDown burnDown = this.burnDownRepository.findById(id).orElseThrow(null);
            return new ResponseEntity<>(burnDown, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<BurnDown>> getBurnDownListByIssue(Long issueId) {
        try{
            Issues issues = this.issuesRepository.findById(issueId).orElseThrow(null);
            List<BurnDown> burnDowns = this.burnDownRepository.findByIssue(issues);
            return new ResponseEntity<>(burnDowns, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BurnDown> updateBurnDownById(Long id, Integer hours) {
        try{
            BurnDown burnDown = this.burnDownRepository.findById(id).orElseThrow(null);
            burnDown.setHours(hours);
            this.burnDownRepository.save(burnDown);
            return new ResponseEntity<>(burnDown, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<BurnDown> deleteBurnDownById(Long id) {
        try{
            BurnDown burnDown = this.burnDownRepository.findById(id).orElseThrow(null);
            burnDown.setActive(false);
            this.burnDownRepository.save(burnDown);
            return new ResponseEntity<>(burnDown, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
