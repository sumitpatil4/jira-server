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
public class Attachments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String file;

    @ManyToOne
    @JoinColumn(name= "issue_id", referencedColumnName="id")
    private Issues issue;

    private LocalDateTime timeStamp = LocalDateTime.now();

    private boolean active=true;
}
