package com.ivoronline.service;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

  //=========================================================================================================
  // GET PERSON
  //=========================================================================================================
  @Cacheable("PersonCash")
  public String getPerson(int id) {
  
    //LOG
    System.out.println("ENTERED getPerson: " + id);
    
    //RETURN PERSON
    switch (id) {
      case  1: return "John";
      case  2: return "Susan";
      default: return "Unknown";
    }
    
  }

}
