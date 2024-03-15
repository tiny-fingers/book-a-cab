package tinyfingers.simplilearn.distance;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DistanceController.class)
public class DistanceControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetMockDistance() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/mockDistance"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$").isNumber());
  }
}