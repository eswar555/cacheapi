package com.cacheapi;

public interface Cache {
    Object get(String key);
    void put(String key, Object value);
}
