package com.teamtek.jiraserver.ServicesImplementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teamtek.jiraserver.Model.JiraRole;
import com.teamtek.jiraserver.Repository.JiraRoleRepository;
import com.teamtek.jiraserver.Services.JiraRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class JiraRoleServiceImplementation implements JiraRoleService {

    private final JiraRoleRepository jiraRoleRepository;
    private final ObjectMapper objectMapper;



    public JiraRoleServiceImplementation(JiraRoleRepository jiraRoleRepository, ObjectMapper objectMapper) {
        this.jiraRoleRepository = jiraRoleRepository;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<String> addNewRole(String role) {
        JiraRole jiraRole = new JiraRole();
        jiraRole.setRole(role);
        this.jiraRoleRepository.save(jiraRole);
        return new ResponseEntity<>("Role Created Successfully", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<JiraRole>> getAllRole() {
        List<JiraRole> jiraRoles = this.jiraRoleRepository.findAll();
        return new ResponseEntity<>(jiraRoles,HttpStatus.OK);
    }
    @PostConstruct
    public void loadData() throws IOException {
        ClassPathResource resource = new ClassPathResource("JiraRole.json");
        JiraRole[] jiraRoles = objectMapper.readValue(resource.getInputStream(), JiraRole[].class);
        List<JiraRole> jiraRoleList = Arrays.asList(jiraRoles);
        jiraRoleRepository.saveAll(jiraRoleList);
    }
}
