package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Teams;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface TeamRepository extends JpaRepository<Teams,Long> {
    Optional<Teams> findById(Long id);
}
