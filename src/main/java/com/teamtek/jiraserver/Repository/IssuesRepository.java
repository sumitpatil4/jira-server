package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.Sprints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface IssuesRepository extends JpaRepository<Issues, Long> {
    List<Issues> findBySprint(Sprints sprint);

    @Query("SELECT count(*) from Issues i where i.active = true AND i.sprint = :sprint")
    Integer noOfIssuesBySprint(@Param("sprint") Sprints sprint);
}
