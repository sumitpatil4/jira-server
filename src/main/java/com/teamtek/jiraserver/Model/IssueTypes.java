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

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class IssueTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private Integer level;

    @ManyToOne
    @JoinColumn(name= "project_id", referencedColumnName="id")
    @JsonIgnore
    private Projects project;

    private LocalDateTime timeStamp = LocalDateTime.now();

    private boolean active=true;
}
