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
public class BoardSequence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long sequence;

    @ManyToOne
    @JoinColumn(name= "issues_id", referencedColumnName="id")
    private Issues issues;

    @ManyToOne
    @JoinColumn(name= "users_id", referencedColumnName="id")
    private Users users;
}
