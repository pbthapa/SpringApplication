/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.service;

import com.yomari.telecom.model.RoleGroup;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author Dell
 */
public interface RoleGroupService {

    public Page<RoleGroup> findAllByPage(Pageable pageable);

    public RoleGroup findById(Integer id);

    public RoleGroup save(RoleGroup roleGroup);

    public void deleteRoleGroupById(Integer id);

    public RoleGroup findRoleDetailByRoleGroupId(Integer roleGroupId);

    public List<RoleGroup> findAllRoleGroups();
}
