package topic.history.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthcheckController {
  @RequestMapping("/healthcheck")
  public void healthcheck() {
  }
}
