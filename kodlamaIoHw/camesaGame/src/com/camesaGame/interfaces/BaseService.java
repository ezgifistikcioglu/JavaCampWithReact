package com.camesaGame.interfaces;

public interface BaseService<T> {
    boolean addValue(T value);
    boolean updateValue(T value);
    boolean deleteValue(T value);
}
