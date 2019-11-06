package com.cacheapi.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cacheapi.impl.MemoryCacheImpl;

public class MemoryCacheImplTest {

	private MemoryCacheImpl cache;

    private String object = new String("memory");

    @Before
    public void init() {
        cache = new MemoryCacheImpl(10);
    }

    @Test
    public void isDataPresent() {
        assertFalse(cache.isDataPresent("2"));
        cache.put("2", object);
        assertTrue(cache.isDataPresent("2"));
    }
    
    @Test
    public void isEmpty() {
    	for(int i=0;i<9;i++){
    		cache.put(i+"", "input " + i);
    	}
        assertTrue(cache.isEmpty());
        cache.put("11", "input");
        assertFalse(cache.isEmpty());
    }
    
    @Test
    public void checkInputObjectIsExistOrNot() {
        cache.put("1", object);
        assertEquals(object, cache.get("1"));
        assertNull(cache.get("313"));
    }

}