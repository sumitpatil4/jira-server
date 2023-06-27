package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.JiraRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JiraRoleRepository extends JpaRepository<JiraRole,Integer> {
}
