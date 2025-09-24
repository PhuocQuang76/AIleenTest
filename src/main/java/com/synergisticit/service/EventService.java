package com.synergisticit.service;

import com.synergisticit.dto.EventDto;
import com.synergisticit.model.Event;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface EventService {

    public Map<String, Double> getTotal(String by);

    public Event createEvent(EventDto eventDto);

    Event deleteEvent(Long id);
}
