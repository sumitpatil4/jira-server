package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.*;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Repository.LogsRepository;
import com.teamtek.jiraserver.Repository.SprintRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.LogsService;
import com.teamtek.jiraserver.Utils.LogRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class LogsServiceImplementation implements LogsService {

    @Autowired
    private LogsRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private IssuesRepository issuesRepository;

    @Override
    public ResponseEntity<Logs> addLog(LogRequestBody body) {
        try {
            Logs log = new Logs();
            log.setField(body.getField());
            log.setOldValue(body.getOldValue());
            log.setNewValue(body.getNewValue());
            log.setAction(body.getAction());

            Users user  = userRepository.findById(body.getUser()).orElseThrow(null);
            Sprints sprint = sprintRepository.findById(body.getSprint()).orElseThrow(null);
            Issues issue = issuesRepository.findById(body.getIssue()).orElseThrow(null);

            log.setUser(user);
            log.setSprint(sprint);
            log.setIssue(issue);

            Logs create = repository.save(log);

            return new ResponseEntity<Logs>(create, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Logs>((Logs) null, HttpStatus.BAD_REQUEST);
        }

    }

    @Override
    public ResponseEntity<List<Logs>> findAll() {
        return new ResponseEntity<List<Logs>>( repository.findAll(),HttpStatus.OK);
    }


    @Override
    public ResponseEntity<Logs> findById(Long Id) {
        try{
            Logs log = repository.findById(Id).orElseThrow(null);
            System.out.println(log);
            return new ResponseEntity<Logs>( log, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Logs>((Logs) null, HttpStatus.BAD_REQUEST);
        }
    }
}
