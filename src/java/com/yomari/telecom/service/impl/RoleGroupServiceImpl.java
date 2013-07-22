/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.service.impl;

import com.google.common.collect.Lists;
import com.yomari.telecom.model.RoleGroup;
import com.yomari.telecom.repository.RoleGroupRepository;
import com.yomari.telecom.service.RoleGroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author Dell
 */
@Service("roleGroupService")
@Repository
public class RoleGroupServiceImpl implements RoleGroupService {

    @Autowired
    private RoleGroupRepository roleGroupRepository;

    @Override
    public Page<RoleGroup> findAllByPage(Pageable pageable) {
        return roleGroupRepository.findAllRoleGroups(pageable);
    }

    @Override
    public RoleGroup findById(Integer id) {
        return roleGroupRepository.findOne(id);
    }

    @Override
    public RoleGroup save(RoleGroup roleGroup) {
        return roleGroupRepository.save(roleGroup);
    }

    @Override
    public void deleteRoleGroupById(Integer id) {
        roleGroupRepository.delete(id);
    }

    @Override
    public RoleGroup findRoleDetailByRoleGroupId(Integer roleGroupId) {
        RoleGroup roleGroup = roleGroupRepository.findRoleGroupById(roleGroupId);
        roleGroup.setRoleDetailIds(roleGroupRepository.findRoleDetailByRoleGroupId(roleGroupId));
        return roleGroup;
    }

    @Override
    public List<RoleGroup> findAllRoleGroups() {
        return Lists.newArrayList(roleGroupRepository.findAllRoleGroupsOnly());
    }
}
