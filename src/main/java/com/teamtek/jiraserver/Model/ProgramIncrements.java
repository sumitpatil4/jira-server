package com.teamtek.jiraserver.Model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProgramIncrements {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name= "project_id", referencedColumnName="id")
    private Projects project;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();

    private LocalDate startDate;

    private LocalDate endDate;
}
