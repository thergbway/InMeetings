package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements Serializable {
    @Id
    @SequenceGenerator(name = "roleIdSeqGenerator", allocationSize = 1, sequenceName = "role_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roleIdSeqGenerator")
    @Column(name = "id")
    private int id;
    @Column(name = "role_name")
    private String roleName;

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
}