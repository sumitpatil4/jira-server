package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Repository.AttachmentsRepository;
import com.teamtek.jiraserver.Services.AttachmentsService;
import com.teamtek.jiraserver.Utils.AttachmentsRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AttachmentsServiceImplementation implements AttachmentsService {

    @Autowired
    private AttachmentsRepository attachmentsRepository;


    @Override
    public ResponseEntity<Attachments> addAttachment(long id, AttachmentsRequestBody attachmentsRequestBody) {
        return null;
    }
}
