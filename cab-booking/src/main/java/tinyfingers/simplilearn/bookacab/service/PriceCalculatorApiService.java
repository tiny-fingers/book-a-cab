package tinyfingers.simplilearn.bookacab.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import tinyfingers.simplilearn.bookacab.model.Pricing;

@Service
public class PriceCalculatorApiService {
  private final RestTemplate restTemplate;

  @Value("${api.price-calculator.host-url}")
  private String apiHostUrl;

  public PriceCalculatorApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Pricing calculatePrice(Double distance, String vehicleType) {
//    String url = "http://price-calculator/api/calculate?distance=" + distance + "&vehicleType=" + vehicleType;
    String url = apiHostUrl + "/calculate?distance=" + distance + "&vehicleType=" + vehicleType;
    return restTemplate.getForObject(url, Pricing.class);
  }
}
