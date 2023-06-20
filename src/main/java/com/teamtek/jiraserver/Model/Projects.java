package com.teamtek.jiraserver.Model;


import javax.persistence.*;
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
public class Projects {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean active=true;

    private String title;

    @ManyToOne
    @JoinColumn(name= "owner_id", referencedColumnName="id")
    private Users owner;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int sprintNumber=1;

    private int issueNumber=1;

    private LocalDateTime timeStamp = LocalDateTime.now();
}
