package com.skypro.recipebook.service;

import com.skypro.recipebook.model.Recipe;

import javax.naming.NameNotFoundException;

public interface Service {
    String add(Object obj);

    Object get(int id) throws NameNotFoundException;
}
