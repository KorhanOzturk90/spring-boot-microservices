package org.bg.technical.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @RequestMapping(value = "/greetings", produces = "application/json; charset=UTF-8")
  public ObjectNode index() {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode node =  mapper.createObjectNode();
    node.put("", "Greetings");
    return node;
  }

}
