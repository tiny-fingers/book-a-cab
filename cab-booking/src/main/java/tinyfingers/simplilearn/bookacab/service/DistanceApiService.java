package tinyfingers.simplilearn.bookacab.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class DistanceApiService {
  private final RestTemplate restTemplate;

  @Value("${api.distance.host-url}")
  private String apiHostUrl;

  public Double calculateDistance(String pickupLocation, String destination) {
//    String url = "http://distance/api/mockDistance?pickupLocation=" + pickupLocation + "&destination=" + destination;
    String url = apiHostUrl + "/mockDistance?pickupLocation=" + pickupLocation + "&destination=" + destination;
    return restTemplate.getForObject(url, Double.class);
  }
}
