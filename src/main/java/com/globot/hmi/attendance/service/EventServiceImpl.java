package com.globot.hmi.attendance.service;

import com.globot.hmi.attendance.dao.mapper.EventMapper;
import com.globot.hmi.attendance.domain.Event;
import com.globot.hmi.attendance.domain.EventExample;
import com.globot.hmi.attendance.dto.EventIdMapperDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by Ambitous on 2017/11/29.
 */
@Service
public class EventServiceImpl implements IEventService {

    @Autowired
    EventMapper eventMapper;

    @Override
    public List<Event> getAllEvent() {
        EventExample eventExample = new EventExample();
        return eventMapper.selectByExample(eventExample);
    }

    @Override
    public Integer addEvent(Event event) {
        event.setAddTime(new Date());
        event.setModifyTime(new Date());
        return eventMapper.insertSelective(event);
    }

    @Override
    public Event getEventById(Integer id) {
        return eventMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer updateEvent(Event event) {
        event.setModifyTime(new Date());
        return eventMapper.updateByPrimaryKeySelective(event);
    }

    @Override
    public Integer deleteEventById(Integer id) {
        return eventMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<EventIdMapperDTO> getEventIdMapper() {
        return eventMapper.getEventIdMapper();
    }
}
