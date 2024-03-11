package tinyfingers.simplilearn.bookacab.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tinyfingers.simplilearn.bookacab.model.Pricing;

@Service
public class PriceCalculatorApiService {
  private final RestTemplate restTemplate;

  public PriceCalculatorApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Pricing calculatePrice(Double distance, String vehicleType) {
    String url = "http://localhost:8888/api/calculate?distance=" + distance + "&vehicleType=" + vehicleType;
    return restTemplate.getForObject(url, Pricing.class);
  }
}
