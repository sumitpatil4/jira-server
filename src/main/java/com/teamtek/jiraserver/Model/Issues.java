package com.teamtek.jiraserver.Model;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Issues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String issueName;

    private String title;

    private String description;

    private LocalDateTime timeStamp = LocalDateTime.now();

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
    @JsonIgnore
    private Projects project;

    @OneToMany(mappedBy = "parentIssue")
    List<Issues> issuesList;

    @ManyToOne
    @JoinColumn(name= "parent_issue_id", referencedColumnName="id")
    @JsonIgnore
    private Issues parentIssue;

    @ManyToOne
    @JoinColumn(name= "sprint_id", referencedColumnName="id")
    @JsonIgnore
    private Sprints sprint;

}
