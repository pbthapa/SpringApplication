/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.security;

import com.yomari.telecom.model.User;
import com.yomari.telecom.repository.UserRepository;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements org.springframework.security.core.userdetails.UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserFromUsername(username);
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), true, true, true, true,
                getAuthorities(user.getId()));
    }

    private Collection<? extends GrantedAuthority> getAuthorities(Integer id) {
        List<String> rolesList = userRepository.findCommaSeparatedRolesByUserId(id);
        String roles = rolesList.toString().replaceAll("\\[", "").replaceAll("\\]", "");
        return AuthorityUtils.commaSeparatedStringToAuthorityList(roles);
    }
}
