/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.repository;

import com.yomari.telecom.model.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author pushpa
 */
//public interface ContactRepository extends CrudRepository<Contact, Long> {
public interface ContactRepository extends PagingAndSortingRepository<Contact, Long> {

    @Query("select count(c) from Contact c")
    public Long countAllContacts();
}
