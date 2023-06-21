package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Roles;
import com.teamtek.jiraserver.Services.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RolesController {
    @Autowired
    private RolesService rolesService;

    @PostMapping()
    public ResponseEntity<Roles> createNewRole(@RequestBody Roles roles){
        return this.rolesService.createNewRole(roles);
    }

    @GetMapping()
    public ResponseEntity<List<Roles>> getAllRoles(){
        return this.rolesService.getAllRoles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Roles> getRolesById(@PathVariable("id") Long id){
        return this.rolesService.getRolesById(id);
    }

    @PutMapping("/{id}/{role}")
    public ResponseEntity<Roles> updateRoles(@PathVariable("id") Long id, @PathVariable("role") String role){
        return this.rolesService.updateRoles(id, role);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Roles> deleteRoles(@PathVariable("id") Long id){
        return this.rolesService.deleteRoles(id);
    }
}
