package com.synergisticit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.synergisticit.controller.EventController;
import com.synergisticit.dto.EventDto;
import com.synergisticit.model.Event;
import com.synergisticit.service.EventService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
public class EventServiceApplicationTests {
    @Test
    void contextLoads() {
    }

    private MockMvc mockMvc;

    @Mock
    private EventService eventService;

    @InjectMocks
    private EventController eventController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(eventController).build();
    }

    @Test
    void createEvent_ShouldReturnCreatedEvent() throws Exception {
        // Arrange
        EventDto eventDto = new EventDto();
        eventDto.setId(1L);
        eventDto.setName("Test Event");
        eventDto.setLocation("Test Location");
        eventDto.setDuration(20);
        eventDto.setCost(100.0);

        Event savedEvent = new Event();
        savedEvent.setId(1L);
        savedEvent.setName("Test Event");
        savedEvent.setLocation("Test Location");
        savedEvent.setDuration(20);
        savedEvent.setCost(100.0);

        when(eventService.createEvent(any(EventDto.class))).thenReturn(savedEvent);

        // Act & Assert
        mockMvc.perform(post("/event/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(eventDto)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Event"))
                .andExpect(jsonPath("$.location").value("Test Location"))
                .andExpect(jsonPath("$.duration").value(20))
                .andExpect(jsonPath("$.cost").value(100.0));
    }
    @Test
    void createEvent_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        // Arrange
        EventDto invalidEventDto = new EventDto(); // Missing required fields

        // Act & Assert
        mockMvc.perform(post("/event/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidEventDto)))
                .andExpect(status().isBadRequest());
    }

}