package com.teamtek.jiraserver.Repository;


import com.teamtek.jiraserver.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Projects,Long> {
    List<Projects> findByOwnerIdAndActive(String id, boolean status);

}
