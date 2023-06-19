package com.teamtek.jiraserver.Utils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePassUserBody {
    private String userId;
    private String oldPassword;
    private String newPassword;
}
