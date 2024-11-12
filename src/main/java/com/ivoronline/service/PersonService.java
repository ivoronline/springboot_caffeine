package com.ivoronline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Service;
import java.util.Iterator;
import java.util.Map;

@Service
public class PersonService {

  //PROPERTIES
  @Autowired CacheManager cacheManager;

  //=========================================================================================================
  // GET PERSON
  //=========================================================================================================
  @Cacheable(value = "PersonCash")
  public String getPerson(int id, String extra) {
  
    //LOG
    System.out.println("\nENTERED getPerson: " + id);
    
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
  // GET PERSON MANUALLY
  //=========================================================================================================
  public String getPersonManually(int id, String extra) {
  
    //LOG
    System.out.println("\nENTERED getPersonManually: " + id);

    //GET FROM CACHE
    Cache cache = cacheManager.getCache("PersonCash");
    SimpleKey key = new SimpleKey(id, extra);
    if(cache.get(key) != null) {
        System.out.println("Returned from Cache");
        return (String) cache.get(key).get();
    }
    
    //DISPLAY CACHE
    printCache("PersonCash");
    
    //CALCULATE RESULT
    String result;
    switch (id) {
      case  1: result = "John";  break;
      case  2: result = "Susan"; break;
      default: result = "Unknown";
    }
    
    //PUT INTO CACHE
    cache.put(key, result);
    
    //RETURN RESULT
    return result;
    
  }

  //=========================================================================================================
  // PRINT CACHE
  //=========================================================================================================
  public void printCache(String cacheName) {
  
    //GET MAP FROM CACHE
    CaffeineCacheManager caffeineCacheManager = (CaffeineCacheManager) cacheManager; //Get implementation
    CaffeineCache        caffeineCache        = (CaffeineCache) caffeineCacheManager.getCache(cacheName);
    Map<Object, Object>  map                  = caffeineCache.getNativeCache().asMap();

    //ITERATE THROUGH KEYS
    Iterator iterator = map.keySet().iterator();
    while (iterator.hasNext() == true) {
      SimpleKey key = (SimpleKey) iterator.next();
      System.out.println(key + " - " + caffeineCache.get(key).get());
    }
    
  }

}
