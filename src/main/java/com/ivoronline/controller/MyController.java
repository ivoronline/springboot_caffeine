package com.ivoronline.controller;

import com.ivoronline.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {

  //PROPERTIES
  @Autowired PersonService personService;

  //=========================================================================================================
  // GET PERSON
  //=========================================================================================================
  @GetMapping("getPerson")
  public String getPerson(@RequestParam int id) {
    String person = personService.getPerson(id, "Extra");
    return person;
  }

  //=========================================================================================================
  // GET PERSON MANUALLY
  //=========================================================================================================
  @GetMapping("getPersonManually")
  public String getPersonManually(@RequestParam int id) {
    String person = personService.getPersonManually(id, "Extra");
    return person;
  }

}
