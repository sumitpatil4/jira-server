package com.teamtek.jiraserver.Utils;

import com.teamtek.jiraserver.Model.Issues;
import com.teamtek.jiraserver.Model.Sprints;
import com.teamtek.jiraserver.Model.Users;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class LogRequestBody {
    private Long id;
    private String field;
    private String action;
    private String oldValue;
    private String newValue;
    private String user;
    private Long issue;
    private Long sprint;
}
