package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Comments;
import com.teamtek.jiraserver.Model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CommentsRepository extends JpaRepository<Comments, Long> {
    List<Comments>findByIssueAndActive(Issues issue,boolean active);
}
