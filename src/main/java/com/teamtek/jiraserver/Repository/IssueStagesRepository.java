package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueStagesRepository extends JpaRepository<IssueStages,Long> {
    List<IssueStages> findAllByProjectAndActive(Projects projects, boolean active);
    @Query("SELECT s FROM IssueStages s WHERE s.active = true AND s.project = :project ORDER BY s.hierarchy ASC")
    IssueStages findIssueStageWithLeastHierarchy(@Param("project") Long project);
}
