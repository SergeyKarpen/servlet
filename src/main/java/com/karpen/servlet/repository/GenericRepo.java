package com.karpen.servlet.repository;

import java.util.List;

public interface GenericRepo<T, ID>{

    T create(T t);

    T update(T t);

    List<T> getAll();

    T getById(ID id);

    void deleteById(ID id);
}
