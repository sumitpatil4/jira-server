package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Services.ProjectService;
import com.teamtek.jiraserver.Utils.ProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins= "*", allowedHeaders = "*")
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService service;

    @PostMapping("/add-project")
    public ResponseEntity<Projects> addProject(@RequestBody ProjectRequestBody body){
        return service.addProject(body);
    }

    @GetMapping("/owner-{id}")
    public ResponseEntity<List<Projects>> findByOwnerId(@PathVariable String id){
        return service.findByOwnerId(id);

    }

    @GetMapping("/all-project")
    public ResponseEntity<List<Projects>> findAll(){
        return service.findAll();
    }

    @PutMapping("/update-project")
    public ResponseEntity<Projects> updateProject(@RequestBody ProjectRequestBody body){
        return service.updateProject(body);
    }

    @DeleteMapping("/delete-{id}")
    public ResponseEntity<Projects> deleteProject(@PathVariable Long id){
        return service.deleteProject(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Projects> findById(@PathVariable  Long id){
        return service.findById(id);

    }

}
