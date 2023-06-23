package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.IssueStages;
import com.teamtek.jiraserver.Model.IssueTypes;
import com.teamtek.jiraserver.Model.Projects;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueTypesRepository extends JpaRepository<IssueTypes,Long> {
    List<IssueTypes> findAllByProjectAndActive(Projects projects, Boolean active);
}
