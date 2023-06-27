package com.teamtek.jiraserver.Utils;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IssueTypesRequestBody {
    private Long id;
    private String title;
    private Long projectId;
    private Integer level;
}
