package com.merck.demo.repo;

import java.util.Map;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.merck.demo.model.Item;

@Repository
public class ItemRepository {

	
	public static final String KEY = "ITEM";
	 
    private RedisTemplate<String, Item> redisTemplate;
    
    private HashOperations hashOperations;
    
    public ItemRepository(RedisTemplate<String, Item> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }
 
    /**
     * Add an Item object into the Redis Database
     */
    public void addItem(Item item) {
        hashOperations.put(KEY, item.getId(), item);
    }
 
    /**
     * getAllItems()
     * 
     */
 
    public Map<Integer, Item> getAllItems() {
        return hashOperations.entries(KEY);
    }
}
