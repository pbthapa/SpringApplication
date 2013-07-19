/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.repository;

import com.yomari.telecom.model.RoleGroup;
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
public interface RoleGroupRepository extends PagingAndSortingRepository<RoleGroup, Integer> {

    @Query("select new RoleGroup(r.id, r.name, r.description) from RoleGroup r")
    public Page<RoleGroup> findAllRoleGroups(Pageable pageable);

    public List<BigDecimal> findRoleDetailByRoleGroupId(Integer id);

    @Query("select new RoleGroup(r.id, r.name, r.description) from RoleGroup r"
    + " where r.id = :id")
    public RoleGroup findRoleGroupById(@Param("id") Integer roleGroupId);
}
