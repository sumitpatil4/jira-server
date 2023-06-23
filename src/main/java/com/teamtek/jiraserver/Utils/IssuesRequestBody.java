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
public class IssuesRequestBody {
    private Long id;
    private String title;
    private String description;
    private LocalDate completionDate;
    private Integer storyPoints;
    private Integer estimatedTime;
    private Long issueTypeId;
    private Long issueStageId;
    private String assignedToId;
    private String createdById;
    private Long projectId;
    private Long parentIssueId;
    private Long sprintId;
}
