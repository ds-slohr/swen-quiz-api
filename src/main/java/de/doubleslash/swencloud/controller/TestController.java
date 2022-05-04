package de.doubleslash.swencloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/foos")
public class TestController {

  @GetMapping
  public String findAll() {
    return "test";
  }

}
