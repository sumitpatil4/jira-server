package com.teamtek.jiraserver.Repository;


import com.teamtek.jiraserver.Model.Projects;
import com.teamtek.jiraserver.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    List<Projects> findByOwnerId(String id);

}
