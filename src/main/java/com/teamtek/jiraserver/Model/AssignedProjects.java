package com.teamtek.jiraserver.Model;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

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

    @ManyToOne
    @JoinColumn(name= "role_id", referencedColumnName="id")
    private Roles role;



}
