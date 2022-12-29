package org.binar.userservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


public class UsersDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;
    private Long id;
    private String username;
    private String email;

    @JsonIgnore
    private String password;

    public Collection<? extends GrantedAuthority> authorities;

    public UsersDetailsImpl(Long id, String username, String email, String password,
                           Collection<? extends GrantedAuthority> authorities){
        this.id = id;
        this.email = email;
        this.password = password;
        this.username = username;
        this.authorities = authorities;
    }

        public static UsersDetailsImpl build(Users user){
            List<GrantedAuthority> authorities = user.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority((role.getName().name()))
                            ).collect(Collectors.toList());
            return new UsersDetailsImpl(
                    Long.valueOf(user.getId()),
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    authorities
            );
        }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getEmail(){
        return email;
    }

    public Long getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
