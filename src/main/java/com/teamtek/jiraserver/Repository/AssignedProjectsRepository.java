package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.AssignedProjects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssignedProjectsRepository extends JpaRepository<AssignedProjects, Long> {

}
