package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.LinkedIssue;
import com.teamtek.jiraserver.Model.Logs;
import com.teamtek.jiraserver.Utils.LogRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogsService {
    public ResponseEntity<Logs> addLog(LogRequestBody body);
    public ResponseEntity<List<Logs>> findAll();
    public ResponseEntity<Logs> findById(Long Id);

}
