package com.teamtek.jiraserver.Model;


import javax.persistence.*;
import javax.validation.constraints.Email;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.*;
import java.time.LocalDate;
import java.time.LocalTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users implements UserDetails {

    @Id
    private String id;
    private String fName;
    private String lName;

    private boolean active=true;
    @Email(message = "enter valid email address")
    private String email;
    private String profileImg;
    private String role="USER";
    private String password;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority("USER"));
        if(this.role.equals("ADMIN")){
            authorityList.add(new SimpleGrantedAuthority("ADMIN"));
        }
        return authorityList;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    private LocalDate createdDate = LocalDate.now();
    private LocalTime createdTime = LocalTime.now();
}
