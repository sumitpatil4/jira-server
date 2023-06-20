package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Roles;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RolesService {
    ResponseEntity<Roles> createNewRole(Roles roles);

    ResponseEntity<List<Roles>> getAllRoles();

    ResponseEntity<Roles> getRolesById(Long id);

    ResponseEntity<Roles> updateRoles(Long id, String role);

    ResponseEntity<Roles> deleteRoles(Long id);
}
