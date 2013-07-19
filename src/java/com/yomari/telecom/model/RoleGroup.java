/**
 *
 */
package com.yomari.telecom.model;

import java.io.Serializable;
import java.math.BigDecimal;
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
import javax.validation.constraints.NotNull;
import org.hibernate.annotations.NamedNativeQuery;

/**
 * @author pushpa
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ntc_role_group")
@SqlResultSetMapping(name = "RoleGroup.findRoleDetailByRoleGroupId", columns =
@ColumnResult(name = "role_id"))
@NamedNativeQuery(name = "RoleGroup.findRoleDetailByRoleGroupId",
query = "select role_id from ntc_role_detail_group where role_group_id "
+ "= ?", resultSetMapping = "RoleGroup.findRoleDetailByRoleGroupId")
public class RoleGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = SEQUENCE, generator = "gen_RoleGroup")
    @SequenceGenerator(name = "gen_RoleGroup", sequenceName = "seq_RoleGroup")
    @Column(name = "role_group_id")
    private Integer id;
    @NotNull(message = "*")
    String name;
    @NotNull(message = "*")
    String description;
    @ManyToMany(fetch = FetchType.LAZY, targetEntity = com.yomari.telecom.model.RoleDetail.class)
    @JoinTable(name = "ntc_role_detail_group", joinColumns =
    @JoinColumn(name = "role_group_id"), inverseJoinColumns =
    @JoinColumn(name = "role_id"))
    Set<RoleDetail> roleDetails = new HashSet<RoleDetail>();
    @Transient
    List<BigDecimal> roleDetailIds = new ArrayList<BigDecimal>();

    public RoleGroup() {
    }

    public RoleGroup(Integer id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<RoleDetail> getRoleDetails() {
        return roleDetails;
    }

    public void setRoleDetails(Set<RoleDetail> roleDetails) {
        this.roleDetails = roleDetails;
    }

    public List<BigDecimal> getRoleDetailIds() {
        return roleDetailIds;
    }

    public void setRoleDetailIds(List<BigDecimal> roleDetailIds) {
        this.roleDetailIds = roleDetailIds;
    }
}
