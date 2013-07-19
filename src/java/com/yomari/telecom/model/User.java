/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.yomari.telecom.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.ColumnResult;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.SqlResultSetMapping;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;
import org.hibernate.annotations.NamedNativeQuery;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author Pushpa
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ntc_user")
@SqlResultSetMapping(name = "User.findCommaSeparatedRolesByUserId", columns =
@ColumnResult(name = "name"))
@NamedNativeQuery(name = "User.findCommaSeparatedRolesByUserId", query = "select name from ntc_role_detail "
+ "where role_id in (select role_id from ntc_role_detail_group where role_group_id "
+ "in (select role_group_id from ntc_role_group_to_user where id= ?))", resultSetMapping = "User.findCommaSeparatedRolesByUserId")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "gen_User")
    @SequenceGenerator(name = "gen_User", sequenceName = "seq_User")
    @Column(name = "ID", nullable = false)
    private Integer id;
    @Column(name = "USER_NAME", unique = true, nullable = false)
    @NotEmpty(message = "username Required")
    @Size(min = 5, max = 50, message = "username range is between 5 and 15")
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotEmpty(message = "password Required")
    @Size(min = 5, max = 50, message = "password range is between 5 and 15")
    private String password;
    @Column(name = "ACTIVE", nullable = false)
    private boolean active = true;
    @ManyToMany(fetch = FetchType.EAGER, targetEntity = com.yomari.telecom.model.RoleGroup.class)
    @JoinTable(name = "ntc_role_group_to_user", joinColumns =
    @JoinColumn(name = "ID"), inverseJoinColumns =
    @JoinColumn(name = "role_group_id"))
    Set<RoleGroup> roleGroups = new HashSet<RoleGroup>();
    @Transient
    private List<String> roleDetailList;

    public User() {
    }

    public User(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public User(Integer id, String username, boolean active) {
        this.id = id;
        this.username = username;
        this.active = active;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Set<RoleGroup> getRoleGroups() {
        return roleGroups;
    }

    public void setRoleGroups(Set<RoleGroup> roleGroups) {
        this.roleGroups = roleGroups;
    }

    public List<String> getRoleDetailList() {
        if (roleDetailList == null) {
            roleDetailList = new ArrayList<String>();
        }
        return roleDetailList;
    }

    public void setRoleDetailList(List<String> roleDetailList) {
        this.roleDetailList = roleDetailList;
    }
}
