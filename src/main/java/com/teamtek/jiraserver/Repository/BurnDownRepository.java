package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.BurnDown;
import com.teamtek.jiraserver.Model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BurnDownRepository extends JpaRepository<BurnDown, Long> {
    List<BurnDown> findByIssue(Issues issues);
}
