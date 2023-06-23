package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Utils.AttachmentsRequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface AttachmentsService {

    /*
    1. Add an Attachment
    2. Get Attachment based on Issue Id
    3. Delete an Attachment
    4.
     */
    ResponseEntity<Attachments> addAttachment(long id, AttachmentsRequestBody attachmentsRequestBody);
}
