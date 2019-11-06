package com.cacheapi.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.cacheapi.impl.FileSystemCacheImpl;


public class FileSystemCacheImplTest {
	private FileSystemCacheImpl cache;

	private String object = new String("file");

    @Before
    public void init() {
        cache = new FileSystemCacheImpl(10);
    }
    
    @Test
    public void isDataPresent() {
        assertFalse(cache.isDataPresent("1"));
        cache.put("1", object);
        assertTrue(cache.isDataPresent("1"));
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
        assertNull(cache.get("233"));
    }


}
