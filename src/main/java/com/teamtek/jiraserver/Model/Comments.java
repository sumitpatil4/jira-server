package com.teamtek.jiraserver.Model;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Comments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name= "user_id", referencedColumnName="id")
    private Users users;

    @ManyToOne
    @JoinColumn(name= "issue_id", referencedColumnName="id")
    private Issues issue;

    private LocalDateTime timeStamp = LocalDateTime.now();

    private String comment;

    private boolean active=true;
}
