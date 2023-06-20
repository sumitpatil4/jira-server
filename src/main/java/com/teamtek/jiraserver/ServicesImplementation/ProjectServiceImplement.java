package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.ProjectRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.ProjectService;
import com.teamtek.jiraserver.Utils.ProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProjectServiceImplement implements ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;


    // Create new project
    public ResponseEntity<Projects> addProject(ProjectRequestBody body){
        Users owner = userRepository.findById(body.getOwner()).orElseThrow(null);
        Projects project = new Projects();
        project.setTitle(body.getTitle());
        project.setOwner(owner);
        project.setDescription(body.getDescription());
        project.setStartDate(body.getStartDate());
        project.setEndDate(body.getEndDate());

        Projects created = repository.save(project);

        return new ResponseEntity<Projects>(created, HttpStatus.CREATED);

    }

    //get all projects of particular owner by userId
    public ResponseEntity<List<Projects>> findByOwnerId(String id){
        return new ResponseEntity<List<Projects>>(repository.findByOwnerId(id), HttpStatus.OK);
    }

    // get all the projects
    public ResponseEntity<List<Projects>> findAll(){
        return new ResponseEntity<List<Projects>>(repository.findAll(), HttpStatus.OK);
    }

    //Update project
    public ResponseEntity<Projects> updateProject(ProjectRequestBody body){
        Users owner = userRepository.findById(body.getOwner()).orElseThrow(null);
        Projects existingProject = repository.findById(body.getId()).orElseThrow(null);
        existingProject.setTitle(body.getTitle());
        existingProject.setOwner(owner);
        existingProject.setDescription(body.getDescription());
        existingProject.setStartDate(body.getStartDate());
        existingProject.setEndDate(body.getEndDate());

        Projects updatedProject = repository.save(existingProject);

        return new ResponseEntity<Projects>(updatedProject, HttpStatus.CREATED);

    }

    //Delete project by making active status to false
    public ResponseEntity<Projects> deleteProject(Long id){
        Projects project = repository.findById(id).orElseThrow(null);
        project.setActive(false);
        Projects updatedProject = repository.save(project);
        return new ResponseEntity<>(updatedProject,HttpStatus.NO_CONTENT);
    }

    //Get projects by ID
    public ResponseEntity<Projects> findById(Long id){
        Projects project = repository.findById(id).orElseThrow(null);
        return new ResponseEntity<Projects>(project, HttpStatus.OK);
    }




}
