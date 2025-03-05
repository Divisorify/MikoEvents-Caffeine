package com.example.MikoEvents.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/demo-controller")
public class DemoController {

  @GetMapping
  @ApiOperation(value = "sayHello")
  public String sayHello() {
    return "Hello from secured endpoint";
  }

}
