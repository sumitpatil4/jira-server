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
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active=true;

    private String title;

    @ManyToOne
    @JoinColumn(name= "owner_id", referencedColumnName="id")
    @JsonIgnore
    private Users owner;

    @OneToOne
    @JoinColumn(name = "active_sprint")
    private Sprints activeSprint;

    @OneToMany(mappedBy = "project")
    List<Sprints> sprintsList;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int sprintNumber;

    private int issueNumber;

    private LocalDateTime timeStamp = LocalDateTime.now();
}
