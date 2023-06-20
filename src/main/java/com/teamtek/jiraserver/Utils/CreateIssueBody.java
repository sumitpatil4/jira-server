package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateIssuesBody {
    private String title;
    private String description;
    private LocalDate createdDate;
    private LocalDate completionDate;
    private int storyPoints;
    private int estimatedTime;
    private Long issueTypeId;
    private Long issueStageId;
    private Long assignedToId;
    private Long userId;
    private Long projectId;
    private Long parentIssueId;
    private Long sprintId;
}
