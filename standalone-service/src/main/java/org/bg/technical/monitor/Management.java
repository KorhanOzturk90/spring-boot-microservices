package org.bg.technical.monitor;

import org.springframework.boot.actuate.endpoint.AbstractEndpoint;
import org.springframework.stereotype.Component;

@Component
public class Management extends AbstractEndpoint {
  private final static String jsonLoad = "Hello Management";

  //Note Id cannot contain '-'
  public Management() {
    super("internal_greetings");
  }

  @Override
  public String invoke() {
    return jsonLoad;
  }
}
