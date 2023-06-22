package com.teamtek.jiraserver.Utils;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AssignedProjectRequestBody {
    private String email;
    private int capacity;
    private LocalDate endDate;
    private long roleId;
}
