package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.domain.Event;
import com.globot.hmi.attendance.dto.EventIdMapperDTO;

import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
public interface IEventService {

    List<Event> getAllEvent();

    Integer addEvent(Event event);

    Event getEventById(Integer id);

    Integer updateEvent(Event event);

    Integer deleteEventById(Integer id);

    List<EventIdMapperDTO> getEventIdMapper();
}
