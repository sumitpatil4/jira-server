package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.LinkedIssue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LinkedIssueRepository extends JpaRepository<LinkedIssue, Long> {
    Optional<LinkedIssue> findByIdAndActive(Long aLong, boolean active);

    List<LinkedIssue> findAllByActive(boolean active);

    List<LinkedIssue> findAllByCauseIssueAndActive(Issues causeIssue, boolean active);
    List<LinkedIssue> findAllByNeedIssueAndActive(Issues needIssue, boolean active);
}
