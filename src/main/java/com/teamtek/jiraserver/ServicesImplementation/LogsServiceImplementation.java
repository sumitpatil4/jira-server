package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Logs;
import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.LogsRepository;
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

    @Override
    public ResponseEntity<Logs> addLog(LogRequestBody body) {
        try {
            Logs log = new Logs();
            log.setField(body.getField());
            log.setOldValue(body.getOldValue());
            log.setNewValue(body.getNewValue());
            log.setAction(body.getAction());
            log.setUser(body.getUser());
            log.setSprint(body.getSprint());
            log.setIssue(body.getIssue());

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
