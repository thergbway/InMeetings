package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "user_total")
public class User implements Serializable {
    @Id
    @SequenceGenerator(name = "userIdSeqGenerator", allocationSize = 1, sequenceName = "user_total_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userIdSeqGenerator")
    @Column(name = "id")
    private int id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", nullable = false)
    @NotNull
    private Role role;

    @Column(name = "login", unique = true, nullable = false, length = 40)
    @NotNull
    private String login;
    @Column(name = "password", nullable = false, length = 40)
    @NotNull
    private String password;

    @Column(name = "first_name", nullable = false, length = 40)
    @NotNull
    private String firstName;
    @Column(name = "last_name", nullable = false, length = 40)
    @NotNull
    private String lastName;

    public User() {

    }

    public User(Role role, String login, String password, String firstName, String lastName) {
        this.role = role;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", role=" + role +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
