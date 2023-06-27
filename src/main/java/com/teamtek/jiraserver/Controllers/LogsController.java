package com.teamtek.jiraserver.Controllers;


import com.teamtek.jiraserver.Model.Logs;
import com.teamtek.jiraserver.Services.LogsService;
import com.teamtek.jiraserver.Utils.LogRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/api/log")
public class LogsController {

    @Autowired
    private LogsService service;

    @PostMapping("/add-log")
    public ResponseEntity<Logs> addLog(@RequestBody LogRequestBody body){
        return service.addLog(body);
    }

    @GetMapping("/all-log")
    public ResponseEntity<List<Logs>> getAll(){
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Logs> findById(@PathVariable Long id){
        return service.findById(id);
    }

}
