package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @SequenceGenerator(name = "roleIdSeqGenerator", allocationSize = 1, sequenceName = "role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdSeqGenerator")
    @Column(name = "id")
    private int id;

    @Column(name = "role_name", nullable = false, unique = true, length = 40)
    @NotNull
    private String roleName;

    public Role() {
    }

    public Role(String roleName) {

        this.roleName = roleName;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}