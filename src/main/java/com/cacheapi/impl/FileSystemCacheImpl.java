package com.cacheapi.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

import com.cacheapi.Cache;

class FileSystemCacheImpl implements Cache {
    private int capacity = 0;
    private Map<Object, String> data ;
    private Path tempDirectory;

    FileSystemCacheImpl(int capacity) { 
        try {
			// initialize the capacity
			this.capacity = capacity;
			this.data = new HashMap<>(capacity);
			// create the cache directory
			this.tempDirectory = Files.createTempDirectory("tempCache"); 
			this.tempDirectory.toFile().deleteOnExit();
		} catch (Exception e) {
			System.out.println("Error - while creating the cache directoy "+ e.getMessage());
		}
        
        
    }

    //put the data into cache
    @Override
    public void put(String key, Object value) {
    	if(!isEmpty()){
    		System.out.println("Exceeded the capacity");
    	}else{
    		try {
    			File tmpFile = Files.createTempFile(tempDirectory, "", "").toFile();
    			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(tmpFile));
    			out.writeObject(value);
    			out.flush();
    			data.put(key, tmpFile.getName());
    			out.close();
    		} catch (Exception e) {
    			System.out.println("Not able to put the data into cache. Reason - "+ e.getMessage());
    		}
    	}

    }
    
    //Check cache data is empty or not
    public boolean isEmpty() {
        return data.size() < this.capacity;
    }
    //get the data from file system
    @Override
    public Object get(String key) {
        if (isDataPresent(key)) {
            String fileName = data.get(key);
            try (FileInputStream in = new FileInputStream(new File(tempDirectory + File.separator + fileName));
            	ObjectInputStream out = new ObjectInputStream(in)) {
                return (String) out.readObject();
            } catch (Exception e) {
            	System.out.println("Not able to read the data from cache. Reason  - "+e.getMessage());
            }
        }
        return null;
    }

    //Check data is present in cache or not
	public boolean isDataPresent(String input) {
		return data.containsKey(input);
	}

}
