package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterBody {
    private String fname;
    private String lname;
    private String email;
    private String password;
}
