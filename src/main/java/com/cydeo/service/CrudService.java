package com.cydeo.service;

import com.cydeo.dto.RoleDTO;

import java.util.List;

public interface CrudService <T, ID> {

    T save(T user);

    T findById(ID userName);

    List<T> findAll();

    void deleteById(ID username);

    void update(T object);
}
