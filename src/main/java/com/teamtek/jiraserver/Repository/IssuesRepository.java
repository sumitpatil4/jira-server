package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {
}
