package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.JiraRole;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JiraRoleService {
    ResponseEntity<String> addNewRole(String role);

    ResponseEntity<List<JiraRole>> getAllRole();
}

