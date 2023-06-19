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
public class Users {

    @Id
    private String id;
    private String name;
    private String email;
    private String profileImg;
    private String role="USER";
    private String password;

    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();
}
