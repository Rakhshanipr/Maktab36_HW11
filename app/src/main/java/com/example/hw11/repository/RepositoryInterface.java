package com.example.hw11.repository;

import java.util.List;
import java.util.UUID;

public interface RepositoryInterface<E> {
    void add(E e);
    void insertList(List<E> eList);
    void update(E e);
    void delete(E e);
    void delete(UUID uuid);
    E get(UUID uuid);
    List<E> getList();


}
