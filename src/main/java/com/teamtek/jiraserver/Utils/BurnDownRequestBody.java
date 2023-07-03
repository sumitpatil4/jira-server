package com.teamtek.jiraserver.Utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class BurnDownRequestBody {
    private Long id;
    private Integer hours;
    private Long issueId;
}
