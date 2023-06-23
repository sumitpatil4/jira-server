package com.teamtek.jiraserver.Services;

import com.teamtek.jiraserver.Model.Comments;
import com.teamtek.jiraserver.Utils.CommentsRequestBody;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CommentsService {
    public ResponseEntity<Comments>addComment(CommentsRequestBody body);
    public ResponseEntity<Comments>deleteComment(Long commentId);
    public  ResponseEntity<Comments>editComment(CommentsRequestBody body,Long commentId);
    public ResponseEntity<List<Comments>>getAllComments(Long issueId);
}
