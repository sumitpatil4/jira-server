package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Sprints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SprintRepository extends JpaRepository<Sprints,Long> {
    List<Sprints> findByProject(Projects project);
}
