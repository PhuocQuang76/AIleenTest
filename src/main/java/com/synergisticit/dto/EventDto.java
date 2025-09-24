package com.synergisticit.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventDto {
    private Long id;
    private String name;
    private String location;
    private int duration;
    private double cost;
}
