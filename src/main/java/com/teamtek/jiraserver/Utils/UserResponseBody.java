package com.teamtek.jiraserver.Utils;


import com.teamtek.jiraserver.Model.JiraRole;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseBody {

    private String id;
    private String fName;
    private String lName;
    private String email;
    private String profileImg;
    private JiraRole jiraRole;
    private String token;

}
