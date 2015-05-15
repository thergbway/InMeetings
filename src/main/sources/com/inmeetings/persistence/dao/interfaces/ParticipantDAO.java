package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Participant;

import java.util.List;

public interface ParticipantDAO extends GenericDAO<Participant> {
    List<Participant> getAllParticipants();
}
