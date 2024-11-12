package com.ivoronline.service;

import com.github.benmanes.caffeine.cache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Map;

@Service
public class PersonService {

  //PROPERTIES
  @Autowired
  CacheManager cacheManager;

  //=========================================================================================================
  // GET PERSON
  //=========================================================================================================
  @Cacheable("PersonCash")
  public String getPerson(int id) {
  
    //LOG
    System.out.println("ENTERED getPerson: " + id);
    
    //DISPLAY CACHE
    printCache("PersonCash");
    
    //RETURN PERSON
    switch (id) {
      case  1: return "John";
      case  2: return "Susan";
      default: return "Unknown";
    }
    
  }

  //=========================================================================================================
  // PRINT CACHE
  //=========================================================================================================
  public void printCache(String cacheName) {
  
    //GET MAP FROM CACHE
    CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager; //Get implementation
    CaffeineCache caffeineCache = (CaffeineCache) caffeineCacheManager.getCache(cacheName);
    Cache caffeine = caffeineCache.getNativeCache();
    Map<Object, Object> map = caffeine.asMap();
    
    //ITERATE THROUGH KEYS
    Iterator iterator = map.keySet().iterator();
    while (iterator.hasNext() == true) {
      int key = (int) iterator.next();
      System.out.println(key + " - " + caffeineCache.get(key).get());
    }
    
  }

}
