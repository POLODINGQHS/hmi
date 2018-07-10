package com.globot.hmi.attendance.dao.mapper;

import com.globot.hmi.attendance.domain.Event;
import com.globot.hmi.attendance.domain.EventExample;
import java.util.List;

import com.globot.hmi.attendance.dto.EventIdMapperDTO;
import org.apache.ibatis.annotations.Param;

public interface EventMapper {
    int countByExample(EventExample example);

    int deleteByExample(EventExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Event record);

    int insertSelective(Event record);

    List<Event> selectByExample(EventExample example);

    Event selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Event record, @Param("example") EventExample example);

    int updateByExample(@Param("record") Event record, @Param("example") EventExample example);

    int updateByPrimaryKeySelective(Event record);

    int updateByPrimaryKey(Event record);

    List<EventIdMapperDTO> getEventIdMapper();
}