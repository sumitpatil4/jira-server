package com.teamtek.jiraserver.Utils;

import com.teamtek.jiraserver.Model.Sprints;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProjectRequestBody {

    private Long id;

    private String title;

    private String owner;

    private String description;

    private LocalDate startDate;

    private LocalDate endDate;

    private int sprintNumber;

    private int issueNumber;

    private Long activeSprint;
}
