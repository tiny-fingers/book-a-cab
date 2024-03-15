package tinyfingers.simplilearn.cabpricecalculator.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CalculateController.class)
public class CalculateControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void testGetPriceFor4Seat() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/calculate")
                    .param("distance", "100")
                    .param("vehicleType", "4seat"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.distance").value(100))
            .andExpect(jsonPath("$.price").value(150))
            .andExpect(jsonPath("$.vehicleType").value("4seat"));
  }

  @Test
  public void testGetPriceFor7Seat() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/calculate")
                    .param("distance", "100")
                    .param("vehicleType", "7seat"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.distance").value(100))
            .andExpect(jsonPath("$.price").value(250))
            .andExpect(jsonPath("$.vehicleType").value("7seat"));
  }

  @Test
  public void testGetPriceForOther() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/api/calculate")
                    .param("distance", "100")
                    .param("vehicleType", "other"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.distance").value(100))
            .andExpect(jsonPath("$.price").value(300))
            .andExpect(jsonPath("$.vehicleType").value("other"));
  }
}