package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.BoardSequence;
import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.LinkedIssue;
import com.teamtek.jiraserver.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BoardSequenceRepository extends JpaRepository<BoardSequence, Long> {
    BoardSequence findByIssuesAndUsers(Issues issues, Users users);
}
