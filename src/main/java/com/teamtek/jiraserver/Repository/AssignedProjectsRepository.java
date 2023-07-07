package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.AssignedProjects;
import com.teamtek.jiraserver.Model.Teams;
import com.teamtek.jiraserver.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.*;
@Repository
public interface AssignedProjectsRepository extends JpaRepository<AssignedProjects, Long> {

    @Query("SELECT a FROM AssignedProjects a where a.team= :teams AND a.active=true")
    List<AssignedProjects> getAllAssignedByTeam(@Param("teams") Teams teams);

    List<AssignedProjects> findByTeam(Teams teams);
}
