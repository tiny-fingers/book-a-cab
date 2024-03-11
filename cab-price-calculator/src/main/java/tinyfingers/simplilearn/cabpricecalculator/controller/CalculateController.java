package tinyfingers.simplilearn.cabpricecalculator.controller;

import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import tinyfingers.simplilearn.cabpricecalculator.model.Pricing;

@RestController
@Slf4j
@RequestMapping("/api")
public class CalculateController {

  @GetMapping("/calculate")
  public Pricing getPrice(@RequestParam("distance") double distance, @RequestParam("vehicleType") String vehicleType) {
    return switch (vehicleType) {
      case "4seat" -> new Pricing(distance, 1.5 * distance, vehicleType);
      case "7seat" -> new Pricing(distance, 2.5 * distance, vehicleType);
      default -> new Pricing(distance, 3 * distance, "other");
    };
  }
}
