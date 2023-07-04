package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Services.AttachmentsService;
import org.hibernate.engine.jdbc.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RestController
@RequestMapping("api/attachements")
public class AttachmentsController {

    @Autowired
    private AttachmentsService attachmentsService;


    @Value("${project.image}")
    private String path;

    @PostMapping("/addAttachment")
    public ResponseEntity<Attachments> addAttachment(@RequestParam("file") MultipartFile file ,
                                                     @RequestParam("issueId") long issueId){
        return attachmentsService.addAttachment(issueId,file,path);
    }

    @GetMapping(value = "/file/{fileName}",produces = MediaType.ALL_VALUE)
    public void downloadImage(@PathVariable("fileName") String fileName,
                              HttpServletResponse response
    ) throws IOException {
        InputStream resource = this.attachmentsService.getResource(path,fileName);
        response.setContentType(MediaType.ALL_VALUE);
        StreamUtils.copy(resource,response.getOutputStream());
    }

    @GetMapping("/getAttachment/{issueId}")
    public ResponseEntity<List<Attachments>> getAttachmentByIssue(@PathVariable("issueId") long id){
        return attachmentsService.getAttachmentBasedOnIssue(id);
    }

    @PutMapping("/removeAttachment/{attachmentId}")
    public ResponseEntity<String> removeAttachment(@PathVariable("attachmentId") long id){
        return attachmentsService.removeAttachment(id);
    }

}