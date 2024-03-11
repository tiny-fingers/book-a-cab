package tinyfingers.simplilearn.bookacab.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DistanceApiService {
  private final RestTemplate restTemplate;

  public DistanceApiService(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public Double calculateDistance(String pickupLocation, String destination) {
    String url = "http://localhost:9999/api/mockDistance?pickupLocation=" + pickupLocation + "&destination=" + destination;
    return restTemplate.getForObject(url, Double.class);
  }
}
