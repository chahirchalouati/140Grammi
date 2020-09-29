/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grammi140.Utilities;

import Grammi140.Models.User.User;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

/**
 *
 * @author Chahir Chalouati
 */
@Component
public class UserDetailsImpl implements UserDetails {

    @Autowired
    private User user;

    private List<GrantedAuthority> grantedAuthoritys;

    public UserDetailsImpl(User user) {
        this.user = user;
        this.grantedAuthoritys = new LinkedList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        user.getAuthoritieList()
                .forEach(authoritie -> grantedAuthoritys.add(new SimpleGrantedAuthority(authoritie.getAuthoritie())));
        return grantedAuthoritys;
    }

    @Override
    public String getPassword() {

        return this.user.getPassword();

    }

    @Override
    public String getUsername() {
        return this.user.getEmail();
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
