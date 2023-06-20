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
public class AssignedProjects {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName="id")
    private Users users;


    @ManyToOne
    @JoinColumn(name= "team_id", referencedColumnName="id")
    private Teams team;

    private int capacity;

    private LocalDate endDate;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();

    @ManyToOne
    @JoinColumn(name= "role_id", referencedColumnName="id")
    private Roles role;

    private boolean active=true;

}
