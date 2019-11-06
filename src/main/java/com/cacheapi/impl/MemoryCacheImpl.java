package com.cacheapi.impl;

import java.util.HashMap;
import java.util.Map;

import com.cacheapi.Cache;


class MemoryCacheImpl implements Cache {
    private final int capacity;
    private final Map<String, Object> memoryCache;

    MemoryCacheImpl(int capacity) {
    	// initialize the capacity
        this.capacity = capacity;
        this.memoryCache = new HashMap<String, Object>(capacity);
    }
    //get the data from file system
    @Override
    public Object get(String key) {
    	if(isDataPresent(key)){
    		return memoryCache.get(key);
    	}
    	return null;
    }
    //Check data is present in cache or not
    public boolean isDataPresent(String key) {
        return memoryCache.containsKey(key);
    }
    
    @Override
    public void put(String key, Object value) {
    	if(isEmpty()){
    		memoryCache.put(key, value);
    	}else{
    		System.out.println("Not possible to put the data into InMemory");
    	}
    }
    //Check cache data is empty or not
    public boolean isEmpty() {
        return memoryCache.size() < this.capacity;
    }
    
    

}
