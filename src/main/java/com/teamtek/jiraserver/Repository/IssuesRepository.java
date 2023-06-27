package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.Sprints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {
    List<Issues> findBySprint(Sprints sprint);

    List<Issues> findByParentIssue(Long id);
}
