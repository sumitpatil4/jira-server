package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Repository.IssueStagesRepository;
import com.teamtek.jiraserver.Repository.ProjectRepository;
import com.teamtek.jiraserver.Services.IssueStagesService;
import com.teamtek.jiraserver.Utils.IssueStagesRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueStagesServiceImplementation implements IssueStagesService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private IssueStagesRepository issueStagesRepository;

    @Override
    public ResponseEntity<IssueStages> addNewIssueStage(IssueStagesRequestBody issueStagesRequestBody) {
        try{
            Projects projects = this.projectRepository.findById(issueStagesRequestBody.getProjectId()).orElseThrow(null);
            IssueStages issueStages = new IssueStages();
            issueStages.setTitle(issueStagesRequestBody.getTitle());
            issueStages.setHierarchy(issueStagesRequestBody.getHierarchy());
            issueStages.setProject(projects);
            issueStagesRepository.save(issueStages);
            return new ResponseEntity<>(issueStages, HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<IssueStages>> findByProject(Long projectId) {
        try{
            Projects projects = this.projectRepository.findById(projectId).orElseThrow(null);
            List<IssueStages> issueStages = issueStagesRepository.findAllByProjectAndActive(projects, true);
            return new ResponseEntity<>(issueStages, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    @Override
//    public ResponseEntity<IssueStages> updateIssueStage(IssueStages issueStages) {
//        return null;
//    }

    @Override
    public ResponseEntity<IssueStages> deleteIssueStage(Long id) {
        try{
            IssueStages issueStage = this.issueStagesRepository.findById(id).orElseThrow();
            List<IssueStages> issueStages = this.issueStagesRepository.findAllByProjectAndActive(issueStage.getProject(), true);
            for(int i=0; i<issueStages.size(); i++){
                if(issueStages.get(i).getHierarchy()>issueStage.getHierarchy()){
                    issueStages.get(i).setHierarchy(issueStages.get(i).getHierarchy()-1);
                    this.issueStagesRepository.save(issueStages.get(i));
                }
            }
            issueStage.setActive(false);
            this.issueStagesRepository.save(issueStage);
            return new ResponseEntity<>(issueStage, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<IssueStages> findLeastHierarchyStageOfProject(Long id) {
        try{
            Projects projects = this.projectRepository.findById(id).orElseThrow(null);
            Pageable pageable = PageRequest.of(0,1);
            List<IssueStages> issueStages = issueStagesRepository.findIssueStageWithLeastHierarchy(projects, pageable);
            return new ResponseEntity<>(issueStages.get(0), HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<String> updateHierarchy(Long projectId, Integer start, Integer end) {
        try{
            Projects projects = this.projectRepository.findById(projectId).orElseThrow(null);
            if(start<end){
                IssueStages issueStages = this.issueStagesRepository.findByProjectAndActiveAndHierarchy(projects, true, start);
                for(int i=start+1; i<=end; i++){
                    IssueStages issueStages1 = this.issueStagesRepository.findByProjectAndActiveAndHierarchy(projects, true, i);
                    issueStages1.setHierarchy(i-1);
                    this.issueStagesRepository.save(issueStages1);
                }
                issueStages.setHierarchy(end);
                this.issueStagesRepository.save(issueStages);
            }
            if(start>end){
                IssueStages issueStages = this.issueStagesRepository.findByProjectAndActiveAndHierarchy(projects, true, end);
                for(int i=start; i<end; i++){
                    IssueStages issueStages1 = this.issueStagesRepository.findByProjectAndActiveAndHierarchy(projects, true, i);
                    issueStages1.setHierarchy(i+1);
                    this.issueStagesRepository.save(issueStages1);
                }
                issueStages.setHierarchy(start);
                this.issueStagesRepository.save(issueStages);
            }
            return new ResponseEntity<>("Hierarchy Updated Successfully", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
