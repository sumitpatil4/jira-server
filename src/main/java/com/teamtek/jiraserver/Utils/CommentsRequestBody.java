package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CommentsRequestBody {

    private String userId;
    private Long issueId;
    private String comment;
}
