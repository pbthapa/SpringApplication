/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.service;

import com.yomari.telecom.model.User;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Dell
 */
public interface UserService {

    List<User> findAll();

    User findById(Integer id);

    User save(User user);

    public Page<User> findAllByPage(Pageable pageable);

    public User findUserByUserId(Integer id);
}
