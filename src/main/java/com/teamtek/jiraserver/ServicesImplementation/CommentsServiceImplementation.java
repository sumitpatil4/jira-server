package com.teamtek.jiraserver.ServicesImplementation;

import com.teamtek.jiraserver.Controllers.CommentsController;
import com.teamtek.jiraserver.Model.Comments;
import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.Users;
import com.teamtek.jiraserver.Repository.CommentsRepository;
import com.teamtek.jiraserver.Repository.IssuesRepository;
import com.teamtek.jiraserver.Repository.UserRepository;
import com.teamtek.jiraserver.Services.CommentsService;
import com.teamtek.jiraserver.Utils.CommentsRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImplementation implements CommentsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private IssuesRepository issuesRepository;

    @Autowired
    private CommentsRepository commentsRepository;

    @Override
    public ResponseEntity<Comments>addComment(CommentsRequestBody body){
        try{
            Users user=userRepository.findById(body.getUserId()).orElseThrow(null);
            Issues issue=issuesRepository.findById(body.getIssueId()).orElseThrow(null);
            Comments comment=new Comments();
            comment.setUsers(user);
            comment.setIssue(issue);
            comment.setComment(body.getComment());
            commentsRepository.save(comment);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Comments>deleteComment(Long commentId){
        try{
            Comments comment=commentsRepository.findById(commentId).orElseThrow(null);
            comment.setActive(false);
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<Comments>editComment(CommentsRequestBody body,Long commentId){
        try{
            Comments comment=commentsRepository.findById(commentId).orElseThrow(null);
            comment.setComment(body.getComment());
            return new ResponseEntity<>(comment,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<List<Comments>>getAllComments(Long issueId){
        try{
            Issues issue=issuesRepository.findById(issueId).orElseThrow(null);
            List<Comments>allComments=commentsRepository.findByIssueAndActive(issue,true);
            return new ResponseEntity<>(allComments,HttpStatus.OK);
        }
        catch(Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
}
