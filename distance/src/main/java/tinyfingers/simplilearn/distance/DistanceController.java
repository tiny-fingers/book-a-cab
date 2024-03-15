package tinyfingers.simplilearn.distance;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api")
public class DistanceController {

  @GetMapping("/mockDistance")
  public double getMockDistance() {
    Random random = new Random();
    return 0.0 + (100.0 - 0.0) * random.nextDouble();
  }
}

