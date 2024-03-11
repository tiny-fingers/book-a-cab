package tinyfingers.simplilearn.cabpricecalculator.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pricing {
  private double distance;
  private double price;
  private String vehicleType;
}
