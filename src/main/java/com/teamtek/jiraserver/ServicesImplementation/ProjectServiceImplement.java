package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.*;
import com.teamtek.jiraserver.Repository.*;
import com.teamtek.jiraserver.Services.ProjectService;
import com.teamtek.jiraserver.Utils.ProjectRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ProjectServiceImplement implements ProjectService {
    @Autowired
    private ProjectRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private IssueStagesRepository issueStagesRepository;

    @Autowired
    private IssueTypesRepository issueTypesRepository;


    // Create new project
    public ResponseEntity<Projects> addProject(ProjectRequestBody body){
        try {
            Users owner = userRepository.findById(body.getOwner()).orElseThrow(null);
            Projects project = new Projects();
            project.setTitle(body.getTitle());
            project.setOwner(owner);
            System.out.println(owner.getFName());
            project.setDescription(body.getDescription());
            project.setStartDate(body.getStartDate());
            project.setEndDate(body.getEndDate());
            project.setActiveSprint(null);
            Projects created = repository.save(project);

            createIssueStage(project);

            createIssueTypes(project);

            return new ResponseEntity<Projects>(created, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Projects>((Projects) null, HttpStatus.BAD_REQUEST);
        }


    }

    public void createIssueTypes(Projects project){
        String[] types = {"Bug", "Story", "Task", "Sub task", "Epic"};
        for (String x: types){
            IssueTypes issueType = new IssueTypes();
            issueType.setProject(project);
            issueType.setTitle(x);
            issueTypesRepository.save(issueType);
        }
    }

    public void createIssueStage(Projects project){
        String[] stages = {"Done", "In Progress", "To Do"};
        for (int i=0;i<stages.length;i++){
            IssueStages issueStages = new IssueStages();
            issueStages.setProject(project);
            issueStages.setTitle(stages[i]);
            issueStages.setHierarchy(i);
            issueStagesRepository.save(issueStages);
        }
    }

    //get all projects of particular owner by userId
    public ResponseEntity<List<Projects>> findByOwnerId(String id){
        List<Projects> projects = repository.findByOwnerId(id);
        List<Projects> out = new ArrayList<>();
        for (Projects project: projects){
            if (project.isActive()){
                out.add(project);
            }
        }
        return new ResponseEntity<List<Projects>>(out, HttpStatus.OK);
    }

    // get all the projects
    public ResponseEntity<List<Projects>> findAll(){
        List<Projects> projects = repository.findAll();
        List<Projects> out = new ArrayList<>();
        for (Projects project: projects){
            if (project.isActive()){
                out.add(project);
            }
        }
        return new ResponseEntity<List<Projects>>(out, HttpStatus.OK);
    }

    //Update project
    public ResponseEntity<Projects> updateProject(ProjectRequestBody body){
        try {
            Users owner = userRepository.findById(body.getOwner()).orElseThrow(null);
            Projects existingProject = repository.findById(body.getId()).orElseThrow(null);
            existingProject.setTitle(body.getTitle());
            existingProject.setOwner(owner);
            existingProject.setDescription(body.getDescription());
            existingProject.setStartDate(body.getStartDate());
            existingProject.setEndDate(body.getEndDate());
            existingProject.setSprintNumber(body.getSprintNumber());
            existingProject.setIssueNumber(body.getIssueNumber());

            Sprints activeSprints = sprintRepository.findById(body.getActiveSprint()).orElseThrow(null);

            existingProject.setActiveSprint(activeSprints);

            Projects updatedProject = repository.save(existingProject);

            return new ResponseEntity<Projects>(updatedProject, HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<Projects>((Projects) null, HttpStatus.BAD_REQUEST);
        }


    }

    //Delete project by making active status to false
    public ResponseEntity<Projects> deleteProject(Long id){
        try {
            Projects project = repository.findById(id).orElseThrow(null);
            project.setActive(false);
            Projects updatedProject = repository.save(project);
            return new ResponseEntity<>(updatedProject,HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<Projects>((Projects) null, HttpStatus.BAD_REQUEST);
        }

    }

    //Get projects by ID
    public ResponseEntity<Projects> findById(Long id){
        try {
            Projects project = repository.findById(id).orElseThrow(null);
            return new ResponseEntity<Projects>(project, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Projects>((Projects) null, HttpStatus.BAD_REQUEST);
        }
    }




}
