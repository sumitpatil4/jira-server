package com.teamtek.jiraserver.Model;


import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();

    private boolean active=true;

    private LocalDate completionDate;

    private int storyPoints;

    private int estimatedTime;

    @ManyToOne
    @JoinColumn(name= "issue_type_id", referencedColumnName="id")
    private IssueTypes issueType;


    @ManyToOne
    @JoinColumn(name= "issue_stage_id", referencedColumnName="id")
    private IssueStages issueStage;


    @ManyToOne
    @JoinColumn(name= "assigned_user_id", referencedColumnName="id")
    private Users assignedTo;

    @ManyToOne
    @JoinColumn(name= "created_user_id", referencedColumnName="id")
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name= "project_id", referencedColumnName="id")
    private Projects project;

    @ManyToOne
    @JoinColumn(name= "parent_issue_id", referencedColumnName="id")
    private Issues parentIssue;

    @ManyToOne
    @JoinColumn(name= "sprint_id", referencedColumnName="id")
    private Sprints sprint;

}
