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
public class LinkedIssue {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "cause_issue_id", referencedColumnName="id")
    private Issues causeIssue;

    @ManyToOne
    @JoinColumn(name= "need_issue_id", referencedColumnName="id")
    private Issues needIssue;

    private String description;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();
}
