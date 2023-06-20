package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Repository.RolesRepository;
import com.teamtek.jiraserver.Services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolesServiceImplementation implements RolesService {
    @Autowired
    private RolesRepository rolesRepository;

    @Override
    public ResponseEntity<Roles> createNewRole(Roles roles) {
        Roles newRole = this.rolesRepository.save(roles);
        return new ResponseEntity<>(newRole, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Roles>> getAllRoles() {
        List<Roles> roles = this.rolesRepository.findAll();
        return new ResponseEntity<>(roles, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Roles> getRolesById(Long id) {
        try {
            Roles role = this.rolesRepository.findById(id).orElseThrow(null);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Roles> updateRoles(Long id, String role) {
        try {
            Roles roles = this.rolesRepository.findById(id).orElseThrow(null);
            roles.setRole(role);
            return new ResponseEntity<>(roles, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Roles> deleteRoles(Long id) {
        try {
            Roles role = this.rolesRepository.findById(id).orElseThrow(null);
            this.rolesRepository.delete(role);
            return new ResponseEntity<>(role, HttpStatus.OK);
        }
        catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
