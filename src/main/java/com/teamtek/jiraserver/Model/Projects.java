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

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();
}
