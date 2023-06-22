package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SprintRequestBody {
    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;
    private Long projectId;
    private String sprintGoal;
}
