package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "manager")
public class Manager implements Serializable {
    @Id
    @SequenceGenerator(name = "managerIdSeqGenerator", allocationSize = 1, sequenceName = "manager_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "managerIdSeqGenerator")
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.EAGER, cascade = {PERSIST,DETACH, REFRESH, MERGE})
    @JoinColumn(name = "meeting_id", nullable = false)
    @NotNull
    private Meeting meeting;

    @OneToOne(fetch = FetchType.EAGER, cascade = {PERSIST,DETACH, REFRESH, MERGE})
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    @Column(name = "description", length = 200)
    private String description;

    public Manager() {
    }

    public Manager(Meeting meeting, User user, String description) {
        this.meeting = meeting;
        this.user = user;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Manager{" +
                "id=" + id +
                ", meeting=" + meeting +
                ", user=" + user +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Meeting getMeeting() {
        return meeting;
    }

    public void setMeeting(Meeting meeting) {
        this.meeting = meeting;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
