package com.example.SpringMongoProject;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.SpringMongoProject.Entity.UserEntity;

public class CustomUserDetails implements UserDetails {

    private final UserEntity userEntity;
    private Collection<? extends GrantedAuthority> authorities;

    public CustomUserDetails(UserEntity userEntity, Collection<? extends GrantedAuthority> authorities) {
        this.userEntity = userEntity;
        this.authorities = authorities;
    }

    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Implement logic based on your application's requirements
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Implement logic based on your application's requirements
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Implement logic based on your application's requirements
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Implement logic based on your application's requirements
        return true;
    }
    
    public UserEntity getUserEntity() {
        return this.userEntity;
    }
}
