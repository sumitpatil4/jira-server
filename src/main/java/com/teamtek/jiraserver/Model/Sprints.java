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
public class Sprints {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne()
    @JoinColumn(name= "project_id", referencedColumnName="id")
    @JsonIgnore
    private Projects project;

    @OneToMany(mappedBy = "sprint",fetch = FetchType.EAGER)
    List<Issues> issuesList;

    private boolean completed;

    private LocalDateTime timeStamp = LocalDateTime.now();
    private boolean active=true;

    private String sprintGoal;

}
