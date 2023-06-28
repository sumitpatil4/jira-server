package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Attachments;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;

@Service
public interface AttachmentsService {

    /*
    1. Add an Attachment
    2. Get Attachment based on Issue Id
    3. Delete an Attachment
    4.
     */
    ResponseEntity<Attachments> addAttachment(long id, MultipartFile file, String path);
    InputStream getResource(String path, String fileName) throws FileNotFoundException;

    ResponseEntity<List<Attachments>> getAttachmentBasedOnIssue(long id);

    ResponseEntity<String> removeAttachment(long id);
}
