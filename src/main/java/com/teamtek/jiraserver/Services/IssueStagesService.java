package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Utils.IssueStagesRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueStagesService {
    ResponseEntity<IssueStages> addNewIssueStage(IssueStagesRequestBody issueStagesRequestBody);
    ResponseEntity<List<IssueStages>> findByProject(Long projectId);
//    ResponseEntity<IssueStages> updateIssueStage(IssueStages issueStages);
    ResponseEntity<IssueStages> deleteIssueStage(Long id);
}
