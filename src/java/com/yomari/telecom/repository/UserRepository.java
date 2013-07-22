/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.repository;

import com.yomari.telecom.model.User;
import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author pushpa
 */
public interface UserRepository extends PagingAndSortingRepository<User, Integer> {

    @Query("select new User(u.id, u.username, u.password) from User u "
    + "where u.username = :username and u.active = 1")
    public User findUserFromUsername(@Param("username") String username);

    public List<String> findCommaSeparatedRolesByUserId(Integer id);

    @Query("select new User(u.id, u.username, u.active) from User u")
    public Page<User> findAllUser(Pageable pageable);

    @Query("select new User(u.id, u.username, u.active) from User u "
    + "where u.id = :id")
    public User findUserByUserId(@Param("id") Integer id);

    public List<BigDecimal> findRoleGroupIdsByUserId(Integer id);
}
