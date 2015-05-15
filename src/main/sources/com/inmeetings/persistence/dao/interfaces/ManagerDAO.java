package com.inmeetings.persistence.dao.interfaces;

import com.inmeetings.persistence.dao.entities.Manager;

import java.util.List;

public interface ManagerDAO extends GenericDAO<Manager>{
    List<Manager> getAllManagers();
}
