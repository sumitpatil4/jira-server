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
public class Teams {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;


    @ManyToOne
    @JoinColumn(name= "project_id", referencedColumnName="id")
    private Projects project;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();

    private boolean active=true;



}
