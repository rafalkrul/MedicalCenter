package com.example.medicalcenter.config.security;

import com.example.medicalcenter.model.UserData;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Getter
public class MyUserDetails implements UserDetails {
    private UUID userId;
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;

    public MyUserDetails(UserData userData){
        this.userId = userData.getId();
        this.username = userData.getEmail();
        this.password = userData.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority(userData.getRole()));
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
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
}
