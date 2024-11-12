package com.ivoronline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  //PROPERTIES
  @Autowired CacheManager cacheManager;

  //=========================================================================================================
  // GET PERSON
  //=========================================================================================================
  public String getPerson(int id) {
    
    //LOG
    System.out.println("\nENTERED getPerson: " + id);
    
    //GET FROM CACHE
    Cache cache = cacheManager.getCache("PersonCash");
    if(cache.get(id) != null) {
        System.out.println("Returned from Cache");
        return (String) cache.get(id).get();
    }
    
    //CALCULATE RESULT
    String result;
    switch (id) {
      case  1: result = "John";  break;
      case  2: result = "Susan"; break;
      default: result = "Unknown";
    }
    
    //PUT INTO CACHE
    cache.put(id, result);
    
    //RETURN RESULT
    return result;
    
  }

}
