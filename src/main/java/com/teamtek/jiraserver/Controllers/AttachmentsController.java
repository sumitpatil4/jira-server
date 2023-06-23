package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Services.AttachmentsService;
import com.teamtek.jiraserver.Utils.AttachmentsRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/attachemnts")
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;

    @PostMapping("/addAttachment/{issueId}")
    public ResponseEntity<Attachments> addAttachment(@PathVariable ("issueId")long id, @RequestBody AttachmentsRequestBody attachmentsRequestBody){
        return attachmentsService.addAttachment(id, attachmentsRequestBody);
    }

}
