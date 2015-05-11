package com.inmeetings.persistence.dao.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "meeting")
public class Meeting implements Serializable {
    @Id
    @SequenceGenerator(name = "meetingIdSeqGeneretor", allocationSize = 1, sequenceName = "meeting_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "meetingIdSeqGeneretor")
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false, length = 40)
    @NotNull
    private String name;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "end_time")
    private Timestamp endTime;

    @Column(name = "description", length = 2000)
    private String description;

    public Meeting() {
    }

    public Meeting(String name, Timestamp startTime, Timestamp endTime, String description) {
        this.name = name;
        this.startTime = startTime;
        this.endTime = endTime;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Meeting{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
