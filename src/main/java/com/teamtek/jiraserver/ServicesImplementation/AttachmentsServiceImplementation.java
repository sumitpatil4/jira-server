package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Model.Attachments;
import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Repository.AttachmentsRepository;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Services.AttachmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class AttachmentsServiceImplementation implements AttachmentsService {

    @Autowired
    private AttachmentsRepository attachmentsRepository;
    @Autowired
    private IssuesRepository issuesRepository;


    @Override
    public ResponseEntity<Attachments> addAttachment(long id, MultipartFile file,String path) {
        Issues issues=this.issuesRepository.findById(id).orElseThrow();
        String filename= file.getOriginalFilename();

        //random name generate
        String randomID = UUID.randomUUID().toString();
        String fileName1=randomID.concat(filename.substring(filename.lastIndexOf(".")));
        System.out.println(fileName1);
        //FullPath
        String filePath = path+ File.separator+fileName1;
        System.out.println(filePath);
        File f=new File(path);
        if(!f.exists()){
            f.mkdir();
        }
        try {
            Files.copy(file.getInputStream(), Paths.get(filePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        change the url based on post
        String url="http://localhost:4545/api/items/attachments/";
        Attachments attachments=new Attachments();
        attachments.setFile(url+fileName1);
        attachments.setIssue(issues);
        Attachments attachments1=this.attachmentsRepository.save(attachments);
        return new ResponseEntity<>(attachments1, HttpStatus.OK);


    }

    @Override
    public InputStream getResource(String path, String fileName) throws FileNotFoundException {
        String fullPath = path+File.separator+fileName;
        InputStream is=new FileInputStream(fullPath);
        return is;
    }
}
