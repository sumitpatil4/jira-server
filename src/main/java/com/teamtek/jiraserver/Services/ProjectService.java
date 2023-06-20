package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Utils.ProjectRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    public ResponseEntity<Projects> addProject(ProjectRequestBody body);
    public ResponseEntity<List<Projects>> findByOwnerId(String id);
    public ResponseEntity<List<Projects>> findAll();
    public ResponseEntity<Projects> updateProject(ProjectRequestBody body);
    public ResponseEntity<Projects> deleteProject(Long id);
    public ResponseEntity<Projects> findById(Long id);
}
