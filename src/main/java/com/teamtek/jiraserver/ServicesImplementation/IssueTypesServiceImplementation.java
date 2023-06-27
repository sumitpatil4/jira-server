package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.IssueTypes;
import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Repository.IssueTypesRepository;
import com.teamtek.jiraserver.Repository.ProjectRepository;
import com.teamtek.jiraserver.Services.IssueTypesService;
import com.teamtek.jiraserver.Utils.IssueTypesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueTypesServiceImplementation implements IssueTypesService {
    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueTypesRepository issueTypesRepository;

    @Override
    public ResponseEntity<IssueTypes> createNewIssueType(IssueTypesRequestBody issueTypesRequestBody) {
        try{
            Projects projects =this.projectRepository.findById(issueTypesRequestBody.getProjectId()).orElseThrow(null);
            IssueTypes issueTypes=new IssueTypes();
            issueTypes.setTitle(issueTypesRequestBody.getTitle());
            issueTypes.setProject(projects);
            issueTypes.setLevel(issueTypesRequestBody.getLevel());
            issueTypesRepository.save(issueTypes);
            return new ResponseEntity<>(issueTypes, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<IssueTypes>> findByProject(Long projectId) {
        try{
            Projects projects = this.projectRepository.findById(projectId).orElseThrow(null);
            List<IssueTypes> issueTypes = issueTypesRepository.findAllByProjectAndActive(projects,true);
            return new ResponseEntity<>(issueTypes, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<IssueTypes> deleteIssueType(Long id) {
        try{
            IssueTypes issueTypes = this.issueTypesRepository.findById(id).orElseThrow(null);
            issueTypes.setActive(false);
            this.issueTypesRepository.save(issueTypes);
            return new ResponseEntity<>(issueTypes, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<IssueTypes>> findByProjectAndLevel(Long projectId, Integer level) {
        try{
            Projects projects = this.projectRepository.findById(projectId).orElseThrow(null);
            List<IssueTypes> issueTypes = issueTypesRepository.findAllByProjectAndLevelAndActive(projects,level, true);
            return new ResponseEntity<>(issueTypes, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
