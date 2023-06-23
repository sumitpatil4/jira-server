package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Attachments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentsRepository extends JpaRepository<Attachments, Long> {
}
