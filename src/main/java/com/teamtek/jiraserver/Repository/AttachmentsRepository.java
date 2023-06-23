package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Model.Issues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, Long> {

    @Query("SELECT a FROM Attachments a where a.issue= :issues AND a.active=true")
    List<Attachments> listOfAttachmentsByIssue(@Param("issues") Issues issues);
}
