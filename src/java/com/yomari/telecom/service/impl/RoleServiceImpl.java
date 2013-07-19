/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.service.impl;

import com.google.common.collect.Lists;
import com.yomari.telecom.model.RoleDetail;
import com.yomari.telecom.repository.RoleRepository;
import com.yomari.telecom.service.RoleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author pushpa
 */
@Service("roleService")
@Repository
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Page<RoleDetail> findAllByPage(Pageable pageable) {
        return roleRepository.findAll(pageable);
    }

    @Override
    public RoleDetail findById(Integer id) {
        return roleRepository.findOne(id);
    }

    @Override
    public RoleDetail save(RoleDetail roleDetail) {
        return roleRepository.save(roleDetail);
    }

    @Override
    public void deleteRoleById(Integer id) {
        roleRepository.delete(id);
    }

    @Override
    public List<RoleDetail> findAll() {
        return Lists.newArrayList(roleRepository.findAll());
    }
}
