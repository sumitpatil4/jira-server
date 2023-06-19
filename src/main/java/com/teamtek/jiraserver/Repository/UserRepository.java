package com.teamtek.jiraserver.Repository;

import com.teamtek.jiraserver.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users,String> {
    @Query("SELECT u from Users u where u.email= :email")
    Users findByEmail(@Param("email") String email);



}
