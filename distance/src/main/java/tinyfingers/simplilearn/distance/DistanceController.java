package tinyfingers.simplilearn.distance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class DistanceController {

  @GetMapping("/mockDistance")
  public double getMockDistance() {
    // return mock distance
    return 10.8;
  }
}

