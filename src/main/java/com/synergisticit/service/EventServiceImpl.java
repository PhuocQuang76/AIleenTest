package com.synergisticit.service;

import com.synergisticit.dto.EventDto;
import com.synergisticit.model.Event;
import com.synergisticit.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    @Autowired
    EventRepository eventRepository;

    public Event createEvent(EventDto eventDto) {
        if(eventDto == null){
            return null;
        } else {
            Event event = new Event();
            event.setId(eventDto.getId());
            event.setName(eventDto.getName());
            event.setLocation(eventDto.getLocation());
            event.setDuration(eventDto.getDuration());
            event.setCost(eventDto.getCost());
            Event savedEvent = eventRepository.save(event);

            return savedEvent;
        }
    }

    public Map<String, Double> getTotal(String by) {
        List<Object[]> results = switch (by.toLowerCase()) {
            case "name" ->
                eventRepository.getTotalGroupByName(by);
            default -> throw new IllegalArgumentException("Invalid field: " + by);
        };
        return results.stream()
                .collect(Collectors.toMap(
                        row -> (String) row[0],  // key (name/location/duration)
                        row -> (Double) row[1]   // sum of costs
                ));
    }

    public Event deleteEvent(Long id) {
        Event event = eventRepository.findById(id).orElse(null);
        if(event == null){
            return null;
        }else{
            eventRepository.delete(event);
            return event;
        }
    }
}
