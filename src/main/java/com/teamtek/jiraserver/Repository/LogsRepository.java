package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Logs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogsRepository extends JpaRepository<Logs,Long> {

}
