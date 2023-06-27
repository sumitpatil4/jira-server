package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LinkedIssueBody {
    private Long causeIssueId;
    private Long needIssueId;
    private String description;
}
