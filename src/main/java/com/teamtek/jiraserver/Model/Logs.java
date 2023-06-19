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
public class Logs {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();

    private String field;

    private String action;

    private String oldValue;

    private String newValue;

    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName="id")
    private Users user;

    @ManyToOne
    @JoinColumn(name= "issue_id", referencedColumnName="id")
    private Issues issue;

    @ManyToOne
    @JoinColumn(name= "sprint_id", referencedColumnName="id")
    private Sprints sprint;



}
