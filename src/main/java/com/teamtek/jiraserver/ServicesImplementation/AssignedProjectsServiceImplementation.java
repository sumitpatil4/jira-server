package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Repository.AssignedProjectsRepository;
import com.teamtek.jiraserver.Services.AssignedProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AssignedProjectsServiceImplementation implements AssignedProjectsService  {

    @Autowired
    private AssignedProjectsRepository assignedProjectsRepository;
}
