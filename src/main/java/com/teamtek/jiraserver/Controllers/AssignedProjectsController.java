package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AssignedProjectsController {

    @Autowired
    private AssignedProjectsService assignedProjectsService;


}
