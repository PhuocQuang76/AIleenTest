package com.synergisticit.controller;

import com.synergisticit.dto.EventDto;
import com.synergisticit.model.Event;
import com.synergisticit.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/event")
public class EventController {
    // New comment -09/26
    EventService eventService;

    @Autowired
    EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/create")
    public ResponseEntity<Event> createEvent(@RequestBody EventDto eventDto) {
        Event savedEvent = eventService.createEvent(eventDto);
        return ResponseEntity.status(200).body(savedEvent);
    }

    @GetMapping("/total")
    public ResponseEntity<Map<String, Double>> getTotalGroupBy(@RequestParam String by) {
        Map<String, Double> eventList = eventService.getTotal(by);
        if (eventList.size() == 0) {
            return ResponseEntity.status(404).body(null);
        }else{
            return ResponseEntity.status(200).body(eventList);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Event> deleteEvent(@PathVariable Long id) {
        Event event = eventService.deleteEvent(id);
        return ResponseEntity.status(200).body(event);

    }

}
