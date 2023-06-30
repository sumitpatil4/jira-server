package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.BurnDown;
import com.teamtek.jiraserver.Utils.BurnDownRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BurnDownService {
    ResponseEntity<BurnDown> createBurnDown(BurnDownRequestBody burnDownRequestBody);
    ResponseEntity<BurnDown> getBurnDownById(Long id);
    ResponseEntity<List<BurnDown>> getBurnDownListByIssue(Long issueId);
    ResponseEntity<BurnDown> updateBurnDownById(Long id, Integer hours);
    ResponseEntity<BurnDown> deleteBurnDownById(Long id);
}
