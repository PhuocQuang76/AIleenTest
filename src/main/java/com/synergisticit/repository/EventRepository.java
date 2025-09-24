package com.synergisticit.repository;

import com.synergisticit.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    // Custom query methods can be added here

    @Query("Select e.name as groupBy, SUM(e.cost) as Total from Event e group by name ")
    List<Object[]> getTotalGroupByName(String name);

//    public List<EventTotalResponse> getTotalGroupByName(String location);
//
//    public List<EventTotalResponse> getTotalGroupByName(String duration);
}