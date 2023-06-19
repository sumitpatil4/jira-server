package com.teamtek.jiraserver.Repository;


import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
}
