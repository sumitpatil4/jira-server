package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.JiraRole;
import com.teamtek.jiraserver.Repository.JiraRoleRepository;
import com.teamtek.jiraserver.Services.JiraRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JiraRoleServiceImplementation implements JiraRoleService {

    @Autowired
    private JiraRoleRepository jiraRoleRepository;

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
}
