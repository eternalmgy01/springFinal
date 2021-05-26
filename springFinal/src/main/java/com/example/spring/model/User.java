package com.example.spring.model;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;


@Entity
@Data
@Table(name = "users")
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String email;
    private String password;
    private double cash;

    @ManyToMany(fetch = FetchType.LAZY, cascade	= CascadeType.PERSIST)
    @JoinTable(name = "user_roles",
            joinColumns	= {
                @JoinColumn(name = "user_id",
                        referencedColumnName = "id")},
            inverseJoinColumns = {
                @JoinColumn(name = "role_id",
                        referencedColumnName = "id")})
    private List<Role> roles;

    @ManyToMany(fetch = FetchType.LAZY, cascade	= CascadeType.PERSIST)
    @JoinTable(name	= "user_games",
            joinColumns	= {
                    @JoinColumn(name = "user_id",
                            referencedColumnName = "id")},
            inverseJoinColumns = {
                    @JoinColumn(name = "game_id",
                            referencedColumnName = "id")})
    private List<Game> games;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return nickname;
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
