package com.teamtek.jiraserver.Services;


import com.teamtek.jiraserver.Model.BoardSequence;
import com.teamtek.jiraserver.Model.Issues;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public interface BoardSequenceService {
    ResponseEntity<BoardSequence> getBoardSequenceById(Long id);

    ResponseEntity<String> assignBoardSequenceNumber(Issues issues);

    ResponseEntity<String> updateSequence(Long id, Long issueStageId1,Integer start,Long issueStageId2, Integer end);
}
