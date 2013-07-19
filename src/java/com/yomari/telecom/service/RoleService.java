/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.service;

import com.yomari.telecom.model.RoleDetail;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author pushpa
 */
public interface RoleService {

    public Page<RoleDetail> findAllByPage(Pageable pageable);

    public RoleDetail findById(Integer id);

    public RoleDetail save(RoleDetail roleDetail);

    public void deleteRoleById(Integer id);

    public List<RoleDetail> findAll();
}
