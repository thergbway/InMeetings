package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "participant")
public class Participant implements Serializable{
    @Id
    @SequenceGenerator(name = "participantIdSeqGenerator", allocationSize = 1, sequenceName = "participant_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participantIdSeqGenerator")
    @Column(name = "id")
    private int id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meeting_id", nullable = false)
    @NotNull
    private Meeting meeting;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @NotNull
    private User user;

    public Participant() {
    }

    public Participant(Meeting meeting, User user) {
        this.meeting = meeting;
        this.user = user;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", meeting=" + meeting +
                ", user=" + user +
                '}';
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
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
}
