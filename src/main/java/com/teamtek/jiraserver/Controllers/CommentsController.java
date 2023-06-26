package com.teamtek.jiraserver.Controllers;

import com.teamtek.jiraserver.Model.Comments;
import com.teamtek.jiraserver.Services.CommentsService;
import com.teamtek.jiraserver.Utils.CommentsRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @PostMapping("/postComment")
    public ResponseEntity<Comments>addComment(@RequestBody CommentsRequestBody body){
            return commentsService.addComment(body);
    }

    @DeleteMapping ("/deleteComment/{commentId}")
    public ResponseEntity<Comments>deleteComment(@PathVariable Long commentId){
            return commentsService.deleteComment(commentId);
    }

    @PutMapping("/editComment/{commentId}")
    public ResponseEntity<Comments>editComment(@RequestBody CommentsRequestBody body,@PathVariable Long commentId){
            return commentsService.editComment(body,commentId);
    }

    @GetMapping("/getAllComments/{issueId}")
    public ResponseEntity<List<Comments>>getAllComments(@PathVariable Long issueId){
            return commentsService.getAllComments(issueId);
    }
}
