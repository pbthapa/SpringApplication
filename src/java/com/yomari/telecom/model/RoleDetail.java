/**
 *
 */
package com.yomari.telecom.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.SEQUENCE;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

/**
 * @author pushpa
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ntc_role_detail", uniqueConstraints =
@UniqueConstraint(columnNames = "name"))
public class RoleDetail implements Serializable {

    @Id
    @Column(name = "role_id")
    @GeneratedValue(strategy = SEQUENCE, generator = "gen_RoleDetail")
    @SequenceGenerator(name = "gen_RoleDetail", sequenceName = "seq_RoleDetail")
    private Integer id;
    @Column(name = "name")
    @NotNull(message = "*")
    private String name;
    @NotNull(message = "*")
    private String description;

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
}
