package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.IssueTypes;
import com.teamtek.jiraserver.Utils.IssueTypesRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IssueTypesService {

    ResponseEntity<IssueTypes> createNewIssueType(IssueTypesRequestBody issueTypesRequestBody);

    ResponseEntity<List<IssueTypes>> findByProject(Long projectId);

    ResponseEntity<IssueTypes> deleteIssueType(Long id);
}
