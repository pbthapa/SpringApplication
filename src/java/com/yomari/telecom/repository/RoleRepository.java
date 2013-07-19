/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.repository;

import com.yomari.telecom.model.RoleDetail;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author pushpa
 */
public interface RoleRepository extends PagingAndSortingRepository<RoleDetail, Integer> {
}
