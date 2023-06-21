package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueStagesRepository extends JpaRepository<IssueStages,Long> {
    List<IssueStages> findAllByProjectAndActive(Projects projects, boolean active);
}
