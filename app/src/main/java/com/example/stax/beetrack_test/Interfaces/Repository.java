package com.example.stax.beetrack_test.Interfaces;

import java.util.List;

/**
 * Created by stax on 26-02-18.
 */

public interface Repository<T> {

    void add(T item);
    void delete(T item);
    List<T> getAll();
}
